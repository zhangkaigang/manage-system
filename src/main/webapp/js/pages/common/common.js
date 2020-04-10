var contextPath = getRootPath ();
function  getRootPath (){
    //获取当前网址，eg：http://localhost:8888/manage-system/user
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，eg：manage-system/user
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8888
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/manage-system
    var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return (localhostPaht + projectName);
}



