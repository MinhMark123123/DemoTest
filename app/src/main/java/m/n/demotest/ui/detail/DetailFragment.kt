package m.n.demotest.ui.detail

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import dagger.hilt.android.AndroidEntryPoint
import m.n.demotest.R
import m.n.demotest.commons.BaseDialogFragment
import m.n.demotest.databinding.FragmentDetailBinding
import java.util.*


@AndroidEntryPoint
class DetailFragment : BaseDialogFragment() {
    lateinit var binding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun provideResIdLayout(): Int = R.layout.fragment_detail
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(detailViewModel)
        setStyle(DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen )
    }

    override fun doBindView(view: View?) {
        binding = FragmentDetailBinding.bind(requireView()).also {
            arguments?.getLong("id")?.let { detailViewModel.id = it }
        }
        binding.iconClose.setOnClickListener {
            dismiss()
        }
    }

    override fun doListenObserver() {
        detailViewModel.consolidatedWeather.observe(this) {
            binding.state.text = getStringFormat(getString(R.string.weather_state_name).toUpperCase(Locale.ROOT), it.weatherStateName)
            binding.temperature.text = getStringFormat(getString(R.string.the_temp).toUpperCase(Locale.ROOT), it.theTemp.toString())
            binding.minTemp.text = getStringFormat(getString(R.string.min_temp).toUpperCase(Locale.ROOT), it.minTemp.toString())
            binding.maxTemp.text = getStringFormat(getString(R.string.max_temp).toUpperCase(Locale.ROOT), it.maxTemp.toString())
            binding.winSpeed.text = getStringFormat(getString(R.string.wind_speed).toUpperCase(Locale.ROOT), it.windSpeed.toString())
            binding.winDirection.text = getStringFormat(getString(R.string.wind_direction).toUpperCase(Locale.ROOT), it.windDirection.toString())
            binding.airPressure.text = getStringFormat(getString(R.string.air_pressure).toUpperCase(Locale.ROOT), it.airPressure.toString())
            binding.humidity.text = getStringFormat(getString(R.string.humidity).toUpperCase(Locale.ROOT), it.humidity.toString())
            binding.visibilyty.text = getStringFormat(getString(R.string.visibility).toUpperCase(Locale.ROOT), it.visibility.toString())
            binding.applicableDate.text = getStringFormat(getString(R.string.applicable_date).toUpperCase(Locale.ROOT), it.applicableDate)
            binding.predictability.text = getStringFormat(getString(R.string.predictability).toUpperCase(Locale.ROOT), it.predictability.toString())
        }
    }

    private fun getStringFormat(s1: String, s2: String): String {
        return String.format(getString(R.string.format_string), s1, s2)
    }

    companion object {
        @JvmStatic
        fun showDialog(id: Long, fragmentTransaction: FragmentTransaction) {
            var newFragment: DialogFragment = DetailFragment()
            var bundle = Bundle()
            bundle.putLong("id", id)
            newFragment.arguments = bundle
            newFragment.show(fragmentTransaction, "dialog")
        }
    }
}