

function checkpw() {
    var checkpw = document.getElementById('repeatedPassword').value;
    var pw = document.getElementById('password').value;
            var div1 = document.getElementById('error');

            if (pw!=checkpw) {
                div1.innerHTML = "<font color='red'>两次输入的密码不一致</font>";
                document.getElementById('error').style.display="inline-block";
                document.getElementById('checkpw').style.display="none";
            } else {
                document.getElementById('checkpw').style.display="inline-block";
                document.getElementById('error').style.display="none";
            }
}
function CheckIntensity(password) {
    var Mcolor, Wcolor, Scolor, Color_Html;
    var m = 0;
    document.getElementById('strong').style.display="inline-block";
    //匹配数字
    if (/\d+/.test(password)) {
        //debugger;
        m++;
    };
    //匹配字母
    if (/[A-Za-z]+/.test(password)) {
        m++;
    };
    //匹配除数字字母外的特殊符号
    if (/[^0-9a-zA-Z]+/.test(password)) {
        m++;
    };

    if (password.length <= 6) { m = 1; }
    if (password.length <= 0) { m = 0; }
    switch (m) {
        case 1:
            Wcolor = "pwd pwd_Weak_c";
            Mcolor = "pwd pwd_c";
            Scolor = "pwd pwd_c pwd_c_r";
            Color_Html = "弱";
            break;
        case 2:
            Wcolor = "pwd pwd_Medium_c";
            Mcolor = "pwd pwd_Medium_c";
            Scolor = "pwd pwd_c pwd_c_r";
            Color_Html = "中";
            break;
        case 3:
            Wcolor = "pwd pwd_Strong_c";
            Mcolor = "pwd pwd_Strong_c";
            Scolor = "pwd pwd_Strong_c pwd_Strong_c_r";
            Color_Html = "强";
            break;
        default:
            Wcolor = "pwd pwd_c";
            Mcolor = "pwd pwd_c pwd_f";
            Scolor = "pwd pwd_c pwd_c_r";
            Color_Html = "无";
            break;
    }
    document.getElementById('pwd_Weak').className = Wcolor;
    document.getElementById('pwd_Medium').className = Mcolor;
    document.getElementById('pwd_Strong').className = Scolor;
    document.getElementById('pwd_Medium').innerHTML = Color_Html;
}