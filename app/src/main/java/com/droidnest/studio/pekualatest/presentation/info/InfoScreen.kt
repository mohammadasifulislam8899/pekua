package com.droidnest.studio.pekualatest.presentation.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.droidnest.studio.pekualatest.R
import com.droidnest.studio.pekualatest.ui.theme.MyBanglaFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoProfileScreen() {
    val colorScheme = MaterialTheme.colorScheme
    val isDarkTheme = isSystemInDarkTheme()

    var selectedInfo by remember { mutableStateOf<Triple<String, String, Color>?>(null) }
    var showSheetContent by remember { mutableStateOf<Pair<String, @Composable () -> Unit>?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .statusBarsPadding()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 60.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.profile_about_us),
                contentDescription = "আমাদের সম্পর্কে প্রোফাইল ছবি",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(
                        width = 3.dp,
                        color = if (isDarkTheme) Color.White else Color.Black,
                        shape = CircleShape
                    )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "মোহাম্মদ আসিফুল ইসলাম",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = MyBanglaFontFamily,
            color = colorScheme.onBackground
        )

        Text(
            text = "প্রধান নির্বাহী কর্মকর্তা, DroidNest Studio",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color(0xFF00C853),
            fontWeight = FontWeight.Medium,
            fontFamily = MyBanglaFontFamily
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "এই অ্যাপে আপনি নিচের বিভাগগুলোর তথ্য জানতে পারবেন:",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = colorScheme.onBackground.copy(alpha = 0.7f),
            fontFamily = MyBanglaFontFamily
        )

        Spacer(modifier = Modifier.height(20.dp))

        val infoItems = listOf(
            Triple(
                "আমাদের সম্পর্কে",
                "এই অ্যাপটি DroidNest Studio কর্তৃক নির্মিত, যার লক্ষ্য পেকুয়া উপজেলার নাগরিকদের জন্য একটি সহজ, দ্রুত ও নির্ভরযোগ্য তথ্যভিত্তিক ডিজিটাল প্ল্যাটফর্ম তৈরি করা।\n\nঅ্যাপের মাধ্যমে ব্যবহারকারীরা স্থানীয় প্রশাসনিক তথ্য, সেবা, জরুরি যোগাযোগ নম্বর এবং আরও অনেক গুরুত্বপূর্ণ তথ্য সহজে অ্যাক্সেস করতে পারবেন।\n\nআমাদের লক্ষ্য একটি স্মার্ট ও তথ্যপ্রযুক্তিনির্ভর পেকুয়া গড়ে তোলা, যেখানে নাগরিক সেবাগুলো আরও সহজলভ্য ও কার্যকর হবে।",
                Color(0xFF00BCD4)
            ),
            Triple(
                "প্রাইভেসি পলিসি",
                "এই অ্যাপ্লিকেশনে ব্যবহারকারীদের গোপনীয়তা ও তথ্যের নিরাপত্তাকে সর্বোচ্চ গুরুত্ব দেওয়া হয়েছে। অ্যাপটি শুধুমাত্র Google Firebase-এর নির্ভরযোগ্য প্রযুক্তি ব্যবহার করে, যা ডেটা সংরক্ষণ ও পরিচালনার জন্য উন্নতমানের নিরাপত্তা ব্যবস্থা প্রদান করে।\n\nএই অ্যাপে কোনো ধরনের ব্যক্তিগত তথ্য যেমন: নাম, মোবাইল নম্বর, ঠিকানা, ইমেইল ইত্যাদি সংগ্রহ বা সংরক্ষণ করা হয় না। ব্যবহারকারীদের কোনো ধরণের নিবন্ধন বা লগইন করার প্রয়োজন নেই।\n\nসকল তথ্য কেবলমাত্র অ্যাপের নির্ধারিত কার্যকারিতা পরিচালনার জন্য ব্যবহৃত হয় এবং কোনোভাবেই তৃতীয় পক্ষের সঙ্গে শেয়ার করা হয় না। আমাদের লক্ষ্য একটি নির্ভরযোগ্য, নিরাপদ এবং তথ্য-সুরক্ষিত অভিজ্ঞতা প্রদান।",
                Color(0xFFE91E63)
            ),
            Triple("পেকুয়া উপজেলা", "ক্লিক করুন", Color(0xFFFFC107)),
            Triple("উপজেলা প্রশাসন", "ক্লিক করুন", Color(0xFF673AB7)),
            Triple(
                "যোগাযোগ করুন",
                "যেকোনো প্রকার সাহায্য, পরামর্শ বা অভিযোগের জন্য আপনি আমাদের সাথে যোগাযোগ করতে পারেন:\n\n📧 ইমেইল: support@pekuaapp.com\n🏢 সরাসরি যোগাযোগ: পেকুয়া উপজেলা কার্যালয়\n\nআমাদের টিম সর্বদা চেষ্টা করে ব্যবহারকারীদের দ্রুত ও কার্যকর সহায়তা প্রদান করতে। আপনার মতামত ও প্রশ্ন আমাদের জন্য গুরুত্বপূর্ণ।",
                Color(0xFF4CAF50)
            )
        )

        infoItems.forEach { (title, description, iconColor) ->
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {
                        when (title) {
                            "পেকুয়া উপজেলা" -> showSheetContent = title to { PekuaSheet() }
                            "উপজেলা প্রশাসন" -> showSheetContent = title to { AdminSheet() }
                            else -> selectedInfo = Triple(title, description, iconColor)
                        }
                    },
                colors = CardDefaults.cardColors(containerColor = colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(iconColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title.first().toString(),
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            fontFamily = MyBanglaFontFamily,
                            color = colorScheme.onSurface
                        )
                        if (description.isNotEmpty()) {
                            Text(
                                text = description,
                                fontSize = 13.sp,
                                color = colorScheme.onSurface.copy(alpha = 0.7f),
                                fontFamily = MyBanglaFontFamily,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }

    selectedInfo?.let { (t, d, _) ->
        AlertDialog(
            onDismissRequest = { selectedInfo = null },
            title = { Text(t, fontFamily = MyBanglaFontFamily, fontWeight = FontWeight.Bold) },
            text = { Text(d, fontFamily = MyBanglaFontFamily, lineHeight = 22.sp) },
            confirmButton = {
                TextButton(onClick = { selectedInfo = null }) {
                    Text("ঠিক আছে", fontFamily = MyBanglaFontFamily)
                }
            },
            containerColor = colorScheme.background,
            titleContentColor = colorScheme.onBackground,
            textContentColor = colorScheme.onBackground
        )
    }

    showSheetContent?.let { (title, content) ->
        ModalBottomSheet(
            onDismissRequest = { showSheetContent = null },
            containerColor = colorScheme.surface,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            Column(Modifier.padding(20.dp)) {
                Text(
                    title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MyBanglaFontFamily,
                    color = colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(16.dp))
                content()
            }
        }
    }
}


@Composable
fun PekuaSheet() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("স্থাপিত: ২৩ এপ্রিল ২০০২", fontFamily = MyBanglaFontFamily)
        Text("আয়তন: 139.6 কিমি²", fontFamily = MyBanglaFontFamily)
        Text("জনসংখ্যা (২০২২): ২,১৪,৩৫৭ জন", fontFamily = MyBanglaFontFamily)
        Text(
            "ইউনিয়ন: ৭টি ( পেকুয়া, মগনামা, রাজাখালী, উজানটিয়া, বারবাকিয়া, টইটং, শিলখালী )",
            fontFamily = MyBanglaFontFamily
        )
    }
}

@Composable
fun AdminSheet() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            "উপজেলা প্রশাসনের আওতাধীন দপ্তরসমূহ:",
            fontWeight = FontWeight.SemiBold,
            fontFamily = MyBanglaFontFamily
        )
        Text("- উপজেলা নির্বাহী অফিসার (ইউএনও)", fontFamily = MyBanglaFontFamily)
        Text("- স্বাস্থ্য কমপ্লেক্স", fontFamily = MyBanglaFontFamily)
        Text("- কৃষি অফিস", fontFamily = MyBanglaFontFamily)
        Text("- মাধ্যমিক শিক্ষা অফিস", fontFamily = MyBanglaFontFamily)
        Text("- এলজিইডি, প্রকৌশল দপ্তর", fontFamily = MyBanglaFontFamily)
        Text("- সমাজসেবা অফিস", fontFamily = MyBanglaFontFamily)
    }
}
