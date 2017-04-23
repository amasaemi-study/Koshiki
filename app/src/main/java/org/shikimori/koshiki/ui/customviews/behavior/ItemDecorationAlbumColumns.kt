package org.shikimori.koshiki.ui.customviews.behavior

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class ItemDecorationAlbumColumns(val gridSpacingPx: Int, val gridSize: Int) : RecyclerView.ItemDecoration() {

    var needLeftSpacing = false

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val frameWidth = ((parent.width - gridSpacingPx * (gridSize - 1)) / gridSize)
        val padding = parent.width / (gridSize - frameWidth)
        val itemPos = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition

        if (itemPos < gridSize)
            outRect.top = 0
        else
            outRect.top = gridSpacingPx

        if (itemPos % gridSize == 0) {
            outRect.left = 0
            outRect.right = padding
            needLeftSpacing = true
        } else if ((itemPos + 1) % gridSize == 0) {
            needLeftSpacing = false
            outRect.right = 0
            outRect.left = padding
        } else if (needLeftSpacing) {
            needLeftSpacing = false
            outRect.left = gridSpacingPx - padding

            if ((itemPos + 2) % gridSize == 0) {
                outRect.right = gridSpacingPx - padding
            } else {
                outRect.right = gridSpacingPx / 2
            }

        } else if ((itemPos + 2) % gridSize == 0) {
            needLeftSpacing = false;
            outRect.left = gridSpacingPx / 2;
            outRect.right = gridSpacingPx - padding
        } else {
            needLeftSpacing = false;
            outRect.left = gridSpacingPx / 2;
            outRect.right = gridSpacingPx / 2;
        }

        outRect.bottom = 0;

        super.getItemOffsets(outRect, view, parent, state)
    }
}