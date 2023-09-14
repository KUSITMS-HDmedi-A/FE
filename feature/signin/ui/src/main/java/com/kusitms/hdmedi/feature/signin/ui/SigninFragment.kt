package com.kusitms.hdmedi.feature.signin.ui

import android.content.res.ColorStateList
import android.util.Log
import com.core.common.BaseFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.kusitms.hdmedi.core.navigation.NavigationGraphFlow
import com.kusitms.hdmedi.core.navigation.ToNavGraph
import com.kusitms.hdmedi.feature.signin.ui.databinding.FragmentSigninBinding

class SigninFragment : BaseFragment<FragmentSigninBinding>(R.layout.fragment_signin) {

    override fun createView(binding: FragmentSigninBinding) {
    }

    override fun viewCreated() {
        binding.lifecycleOwner = this
        initView()
        initClickListener()
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

    private fun initClickListener(){
        binding.btnEmailLogin.btn.setOnClickListener {
            navigateToBottomFlow()
            Snackbar.make(requireContext(), it, "Click!", Snackbar.LENGTH_SHORT).show()
        }

        binding.btnKakaoLogin.btn.setOnClickListener {
            kakaoLogin { isSuccess, msg ->
                if (isSuccess) {
                    Log.i(requireContext().toString(), "카카오 로그인 성공 $msg")
                    successLogin(msg)
                }
                else Log.e(requireContext().toString(), "카카오 로그인 실패 : $msg")
            }
        }
    }

    private fun kakaoLogin(isSuccess: (Boolean, String) -> Unit) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                if (error != null) isSuccess(false, error.toString())
                else if (token != null) isSuccess(true, token.accessToken)
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(requireContext()) { token, error ->
                if (error != null) isSuccess(false, error.toString())
                else if (token != null) isSuccess(true, token.accessToken)
            }
        }
    }

    private fun successLogin(token: String ){
        //viewModel.setUserAccessToken(token)
        navigateToBottomFlow()
    }

    private fun navigateToBottomFlow(){
        (requireActivity() as ToNavGraph).navigateToGraph(NavigationGraphFlow.BottomGraphFlow)
    }

}