package com.droidnest.studio.pekualatest.domain.model

import androidx.annotation.DrawableRes
import com.droidnest.studio.pekualatest.R

sealed class OnBoardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.welcome,
        title = "স্বাগতম",
        description = "Pekua অ্যাপে আপনাকে স্বাগতম! এখানে আপনি পেকুয়া উপজেলার সকল গুরুত্বপূর্ণ সেবা এক জায়গায় খুঁজে পাবেন।"
    )

    object Second : OnBoardingPage(
        image = R.drawable.explore,
        title = "সেবা খুঁজুন",
        description = "পুলিশ, হাসপাতাল, বাস, বিদ্যুৎ অফিসসহ সকল সরকারি-বেসরকারি সেবার তথ্য সহজে ব্রাউজ করুন ও খুঁজে নিন।"
    )

    object Third : OnBoardingPage(
        image = R.drawable.power,
        title = "সহজ যোগাযোগ",
        description = "প্রত্যেক সেবার যোগাযোগ নম্বর ও অবস্থান ম্যাপে দেখুন এবং সরাসরি কল বা লোকেশন ট্র্যাক করুন দ্রুত।"
    )
}


