// 验证码刷新
function changeVerifyCode() {
    $('#kaptchaImage').attr('src', contextPath + '/kaptcha?d='+new Date().getTime());
}

layui.use(['form'], function() {
    console.log('statr2222------')
    var form = layui.form;
    commonFuns.renderForm();
    // 监听表单提交-登录
    form.on("submit(login)",function () {
        console.log('statr111------')
        $("#loginForm").submit();
        return false;
    });
});