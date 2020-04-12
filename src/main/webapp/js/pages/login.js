// 验证码刷新
function changeVerifyCode() {
    $('#kaptchaImage').attr('src', contextPath + '/kaptcha?d='+new Date().getTime());
}