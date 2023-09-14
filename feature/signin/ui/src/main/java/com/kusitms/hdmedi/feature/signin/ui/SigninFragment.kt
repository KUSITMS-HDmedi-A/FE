package com.kusitms.hdmedi.feature.signin.ui

import android.content.res.ColorStateList
import com.core.common.BaseFragment
import com.google.android.material.button.MaterialButton
import com.kusitms.hdmedi.feature.signin.ui.databinding.FragmentSigninBinding

class SigninFragment : BaseFragment<FragmentSigninBinding>(R.layout.fragment_signin) {
    override fun createView(binding: FragmentSigninBinding) {
    }

    override fun viewCreated() {
        binding.lifecycleOwner = this
        initView()
    }

    private fun initView() {
        binding.btnKakaoLogin.apply {
            txtContent = getString(R.string.login_kakao)
            btn.setTextColor(requireContext().getColor(com.core.common.R.color.dark_kakao))
            val color = requireContext().getColor(com.core.common.R.color.yellow_kakao)
            btn.backgroundTintList = ColorStateList.valueOf(color)
            btn.icon = requireContext().getDrawable(R.drawable.ic_kakao)
            btn.iconGravity = MaterialButton.ICON_GRAVITY_TEXT_START
            btn.iconTint = ColorStateList.valueOf(requireContext().getColor(com.core.common.R.color.icon_kakao))
        }
        binding.btnEmailLogin.txtContent = getString(R.string.login_email)
    }

}