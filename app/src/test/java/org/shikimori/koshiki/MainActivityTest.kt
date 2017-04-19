package org.shikimori.koshiki

import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.shikimori.koshiki.ui.activities.MainActivity

/**
 * Created by Александр on 19.04.2017.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class MainActivityTest {

    val activity = Robolectric.setupActivity(MainActivity::class.java)

    @Before
    fun setUp() {

    }

    @Test
    fun activityNotNull() {
        assertNotNull(activity)
    }
}