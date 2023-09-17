package com.kusitms.hdmedi.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kusitms.hdmedi.ui.databinding.FragmentRegisterMedicineDateBinding
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.TimeZone


class RegisterMedicineDateFragment : Fragment() {

    lateinit var fragmentRegisterMedicineDateBinding: FragmentRegisterMedicineDateBinding

    lateinit var navController: NavController

    var startData = ""
    var endData = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentRegisterMedicineDateBinding = FragmentRegisterMedicineDateBinding.inflate(inflater)

        fragmentRegisterMedicineDateBinding.run {
            toolbarRegisterMedicineDate.run {
                title = "약 등록"

                // back 버튼 설정
                setNavigationIcon(R.drawable.arrow_back)

                setNavigationOnClickListener {
                    navController.popBackStack()
                }
            }

            calendarRegisterMedicineDate.setCalendarListener(object : CalendarListener,
                com.archit.calendardaterangepicker.customviews.CalendarListener {

                override fun onDateRangeSelected(startDate: Calendar, endDate: Calendar) {
                    var start = startDate.time.year.toString()
                    var end = endDate.time.year.toString()
                    if((startDate.time.month + 1) < 10) {
                        if((startDate.time.date) < 10) {
                            startData =
                                "20${start.substring(1)}-0${startDate.time.month + 1}-0${startDate.time.date}"
                        } else {
                            startData =
                                "20${start.substring(1)}-0${startDate.time.month + 1}-${startDate.time.date}"
                        }
                    } else {
                        if((startDate.time.date) < 10) {
                            startData =
                                "20${start.substring(1)}-${startDate.time.month + 1}-0${startDate.time.date}"
                        } else {
                            startData =
                                "20${start.substring(1)}-${startDate.time.month + 1}-${startDate.time.date}"
                        }
                    }
                    if((endDate.time.month + 1) < 10) {
                        if((endDate.time.date) < 10) {
                            endData =
                                "20${end.substring(1)}-0${endDate.time.month + 1}-0${endDate.time.date}"
                        } else {
                            endData =
                                "20${end.substring(1)}-0${endDate.time.month + 1}-${endDate.time.date}"
                        }
                    } else {
                        if((endDate.time.date) < 10) {
                            endData =
                                "20${end.substring(1)}-${endDate.time.month + 1}-0${endDate.time.date}"
                        } else {
                            endData =
                                "20${end.substring(1)}-${endDate.time.month + 1}-${endDate.time.date}"
                        }
                    }
                }

                override fun onFirstDateSelected(startDate: Calendar) {
//                    Toast.makeText(requireContext(), "Start Date: " + startDate?.getTime().toString(), Toast.LENGTH_SHORT).show();
//                    var start = startDate.time.year.toString()
//                    var startData  = "20${start.substring(1)}-${startDate.time.month + 1}-${startDate.time.date}"
                }
            })
        }
        return fragmentRegisterMedicineDateBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        fragmentRegisterMedicineDateBinding.run {
            buttonRegisterMedicineDateNext.setOnClickListener {
                var purpose = requireArguments().getString("purpose")
                var medicineList = arrayListOf<String>(purpose.toString(), startData, endData)
                val bundle = bundleOf("medicine" to medicineList)
                navController.navigate(R.id.action_registerMedicineDateFragment_to_registerMedicineInfoFragment, bundle)
            }
        }
    }
}