package com.developersbreach.clientproject.view.intro

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.developersbreach.clientproject.R
import com.developersbreach.clientproject.model.Intro
import com.developersbreach.clientproject.utils.convertToDp
import com.developersbreach.clientproject.view.controller.introToDashboard


@BindingAdapter("bindIntroViewPagerListData")
fun ViewPager2.setIntroViewPagerListData(
    listData: List<Intro>
) {
    val viewPager = IntroViewPagerAdapter(this)
    viewPager.submitList(listData)
    this.adapter = viewPager
}


@BindingAdapter(
    "bindNextIntroPagerListener", "bindSkipIntroPagerListener",
    "bindHideIntroNextTextView"
)
fun TextView.setNextItemClickListener(
    viewPager: ViewPager2,
    skipIntroTextView: TextView,
    intro: Intro
) {
    val nextIntroTextView = this

    nextIntroTextView.setOnClickListener {
        when (nextIntroTextView.text) {
            "Next" -> {
                val currentItem = viewPager.currentItem
                viewPager.setCurrentItem(currentItem + 1, true)
            }
            "Finish" -> {
                navigateToArticleListFragment(nextIntroTextView)
            }
        }
    }

    when (intro.id) {
        1 -> {
            skipIntroTextView.text = context.getText(R.string.skip_intro_pager_text)
            skipIntroTextView.visibility = View.VISIBLE
        }
        2, 3 -> {
            skipIntroTextView.visibility = View.GONE
        }
        4 -> {
            this.text = nextIntroTextView.context.getText(R.string.end_intro_pager_text)
            skipIntroTextView.visibility = View.GONE
        }
    }

    skipIntroTextView.setOnClickListener {
        navigateToArticleListFragment(nextIntroTextView)
    }

    viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)

            when (state) {
                ViewPager2.SCROLL_STATE_IDLE,
                ViewPager2.SCROLL_STATE_SETTLING -> {
                    skipIntroTextView.visibility = View.VISIBLE
                    nextIntroTextView.visibility = View.VISIBLE
                }
                ViewPager2.SCROLL_STATE_DRAGGING -> {
                    skipIntroTextView.visibility = View.INVISIBLE
                    nextIntroTextView.visibility = View.INVISIBLE
                }
            }
        }
    })
}


@BindingAdapter(
    "bindPagerFirstPositionViewer", "bindPagerSecondPositionViewer",
    "bindPagerThirdPositionViewer"
)
fun View.setPagerFirstPositionViewer(
    currentId: Int,
    secondViewer: View,
    thirdViewer: View,
) {
    when (currentId) {
        1 -> adjustViewSize(this)
        2 -> adjustViewSize(secondViewer)
        3 -> adjustViewSize(thirdViewer)
    }
}

private fun View.adjustViewSize(currentView: View) {
    currentView.setBackgroundResource(R.drawable.current_pager_background)
    currentView.layoutParams.height = convertToDp(12.toFloat())
    currentView.layoutParams.width = convertToDp(12.toFloat())
}

private fun TextView.navigateToArticleListFragment(nextIntroTextView: TextView) {
    val context: Context = nextIntroTextView.context

    with(
        context.getSharedPreferences(
            context.getString(R.string.preference_intro_result_key),
            Context.MODE_PRIVATE
        ).edit()
    ) {
        putString(
            context.getString(R.string.preference_intro_status_key),
            context.getString(R.string.preference_intro_fragment_shown_value)
        )
        commit()
    }

    introToDashboard(findNavController())
}