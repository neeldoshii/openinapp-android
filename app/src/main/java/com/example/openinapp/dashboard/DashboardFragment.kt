package com.example.openinapp.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openinapp.R
import com.example.openinapp.databinding.FragmentDashboardBinding
import com.example.openinapp.helperUtils.getCurrentTimeInHours
import com.example.openinapp.model.Analytics

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    val binding get() = _binding!!
    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        //Greets User based on the current time
        val currentTime = getCurrentTimeInHours()
        when (currentTime) {
            in 12..16 -> {
                binding.greetTV.text = getString(R.string.greet_afternoon)
            }
            in 17..20 -> {
                binding.greetTV.text = getString(R.string.greet_evening)
            }
            in 21..23 -> {
                binding.greetTV.text = getString(R.string.greet_night)
            }
            else -> {
                binding.greetTV.text = getString(R.string.greet_morning)
            }
        }


        val analyticsList = mutableListOf(
            Analytics(analyticsIcon = R.drawable.ic_click, analyticsDescription = "Today’s clicks"),
            Analytics(analyticsIcon = R.drawable.ic_location, analyticsDescription = "Top Location"),
            Analytics(analyticsIcon = R.drawable.ic_source, analyticsDescription = "Top source"),
//            Analytics(analyticsIcon = R.drawable.ic_time, analyticsCount = 1, analyticsDescription = "Best Time"),
        )

        val adapter = AnalyticsAdapter(analyticsList)
        binding.analyticsRV.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.analyticsRV.adapter = adapter

        viewModel.linkCreated.observe(viewLifecycleOwner) {
            analyticsList[0] = analyticsList[0].copy(analyticsCount = it)
            adapter.notifyItemChanged(0)
        }

        viewModel.location.observe(viewLifecycleOwner) {
            analyticsList[1] = analyticsList[1].copy(analyticsCount = it)
            adapter.notifyItemChanged(1)
        }

        viewModel.sources.observe(viewLifecycleOwner) {
            analyticsList[2] = analyticsList[2].copy(analyticsCount = it)
            adapter.notifyItemChanged(2)
        }

        viewModel.getApiResponse()


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}