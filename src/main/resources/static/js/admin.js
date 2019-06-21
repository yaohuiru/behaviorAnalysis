$(function(){
    getUserTable();
});

function getUserTable() {
    var tbody = document.getElementById("userTable");
    var tdata = [];
    $.ajax({
        type : 'post', //测试get，正式post
        cache : false,
        dataType: 'json',
        //contentType: 'application/json;charset=UTF-8',
        async:false,
        url:getRootPath_web()+"/user/selectall",
        data: {
        },
        error : function(){
            console.error("出现异常");
        },
        success : function(data){
            tdata = data;
            console.log(data);
        }
    });

    for (var i = 0; i < tdata.length; i++) {
        var tr = document.createElement("tr");
        var td1 = document.createElement("td");
        td1.innerHTML = tdata[i].userNum;
        tr.appendChild(td1);
        var td2 = document.createElement("td");
        td2.innerHTML = tdata[i].userName;
        tr.appendChild(td2);
        var td3 = document.createElement("td");
        td3.innerHTML = tdata[i].userEmail;
        tr.appendChild(td3);
        var td4 = document.createElement("td");
        td4.innerHTML = tdata[i].departmentId;
        tr.appendChild(td4);
        var td5 = document.createElement("td");
        td5.innerHTML = tdata[i].state;
        tr.appendChild(td5);

        tbody.appendChild(tr);
    }
}