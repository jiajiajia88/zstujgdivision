/*修改电话*/
$('#btn_phone_modify').on('click', function () {
    $('#phone_number_modify').removeClass('panel_close');
    $('#phone_number_label').addClass('panel_close');
    $('#btn_phone_modify').addClass('panel_close');
    $('#btn_phone_update').removeClass('panel_close');
    $('#btn_phone_cancel').removeClass('panel_close');
});

/**
 * 取消更新电话号码
 */
$('#btn_phone_cancel').on('click', function () {
    $('#phone_number_modify').addClass('panel_close');
    $('#phone_number_label').removeClass('panel_close');
    $('#btn_phone_modify').removeClass('panel_close');
    $('#btn_phone_submit').addClass('panel_close');
    $('#btn_phone_cancel').addClass('panel_close');
});

/**
 * 更新电话号码
 */
$('#btn_phone_update').on('click', function () {
    alert("跟新");
    var phone_number_modify = $('#phone_number_modify').val();
    $.post("/student/updatePhone", {phoneNumber: phone_number_modify},function (data){
        var result = JSON.parse(data['result']);
        if(result === 200){
            alert("更新手机号成功！");
            window.location.href="/account";
        } else {
            alert("电话号码失败！");
        }
    });
});

/**
 * 补充电话号码
 */
$('#btn_phone_add').on('click', function () {
    var phone_number_add = $('#phone_number_add').val();
    $.post("/student/updatePhone", {phoneNumber: phone_number_add},function (data){
        var result = JSON.parse(data['result']);
        if(result === 200){
            alert("添加手机号成功！");
            window.location.href="/account";
        } else {
            alert("电话号码失败！");
        }
    });
});

/**
 * 更新密码
 */
$('#btn_pwd_update').on('click', function () {
    var origin_pwd = $('#origin_pwd').val();
    var new_pwd = $('#new_pwd').val();
    var repeat_pwd = $('#repeat_pwd').val();

    var message = '';
    if(origin_pwd == ''){
        message = message + '原密码为空！' + "\n";
    } else if (new_pwd == ''){
        message = message + '新密码为空！' + "\n";
    } else if (repeat_pwd == '') {
        message = message + '重复密码为空！'+ "\n";
    } else if (new_pwd != repeat_pwd) {
        message = message + '新密码与重复密码不一致！'+ "\n";
    }

    if(message == ''){
        $.post("/updatePwd", {origin_pwd: origin_pwd,new_pwd: new_pwd,repeat_pwd: repeat_pwd},function (data){
            var result = JSON.parse(data['result']);
            if(result===2){
                alert('原密码不正确！');
            }
            if(result===200){
                alert("修改密码成功！");
                window.location.href="/index";
            }
        });
    } else {
        alert(message);
    }
});

/**
 * species修改后跳转
 */
$('.btn_species_modify').on('click', function () {
    $(this).parent().addClass('panel_close');
    $(this).parent().parent().children().eq(2).addClass('panel_close');
    $(this).parent().parent().children().eq(3).removeClass('panel_close');
    $(this).parent().parent().children().eq(4).removeClass('panel_close');
});


/**
 * species点击取消修改
 */
$('.btn_species_submitout').on('click', function () {
    $(this).parent().addClass('panel_close');
    $(this).parent().parent().children().eq(2).removeClass('panel_close');
    $(this).parent().parent().children().eq(3).addClass('panel_close');
    $(this).parent().parent().children().eq(4).addClass('panel_close');
});

/**
 * species添加
 */
$('.btn_species_add').on('click', function () {
    var species = $(this).parent().parent().children().eq(1).children().eq(0).val();

    if(confirm("增加"+species+"?")){
        $.post('/system/addSpecies',{species : species},function (data) {
            var result = JSON.parse(data['result']);
            if (result === 200) {
                alert("添加成功！");
                window.location.href = "/system/system_basic";
            } else {
                alert("添加" + species + "失败");
            }
        });
    }

});

/**
 * species删除
 */

$('.btn_species_delete').on('click', function () {
    var speciesId = $(this).parent().parent().children().eq(0).val();
    var speciesName = $(this).parent().parent().children().eq(1).text();

    if(confirm("删除"+speciesName+"?")){
        $.post("/system/deleteSpecies", {speciesId: speciesId},function (data){
            var result = JSON.parse(data['result']);
            if(result===200){
                alert("添加大类"+speciesName+"失败！");
                window.location.href="/system/system_basic";
            } else {
                alert("删除大类"+speciesName+"失败！");
            }
        });
    }

});

/**
 * species更新
 */
$('.btn_species_submit').on('click', function () {
    var speciesId = $(this).parent().parent().children().eq(0).val();
    var oldSpeciesName = $(this).parent().parent().children().eq(1).text();
    var newSpeciesName = $(this).parent().parent().children().eq(3).children().eq(0).val();

    if(confirm("是否将"+oldSpeciesName+"更名为"+newSpeciesName+"?")){

        $.post("/system/updateSpecies", {speciesId: speciesId,newSpeciesName: newSpeciesName},function (data){
            var result = JSON.parse(data['result']);
            if(result===200){
                alert("更新密码成功！");
                window.location.href="/system/system_basic";
            } else {
                alert("更新"+oldSpeciesName+"失败！");
            }
        });

    }

});


/**
 * major添加
 */
$('.btn_major_add').on('click', function () {

    var major_name = $(this).parent().parent().children().eq(1).children().eq(0).val();
    var major_species = $(this).parent().parent().children().eq(3).children().eq(0).val();

    if(confirm("增加"+major_name+"?")){
        $.post('/system/addMajor',{major_name :major_name, major_species: major_species},function (data) {
            var result = JSON.parse(data['result']);
            if (result === 200) {
                alert("添加"+major_name+"成功！");
                window.location.href = "/system/system_major_settings";
            } else {
                alert("添加" + major_name + "失败");
            }
        });
    }

});

/**
 * major删除
 */

$('.btn_major_delete').on('click', function () {
    var majorId = $(this).parent().parent().children().eq(0).val();
    var majorName = $(this).parent().parent().children().eq(1).text();

    if(confirm("删除"+majorName+"?")){
        $.post("/system/deleteMajor", {majorId: majorId},function (data){
            var result = JSON.parse(data['result']);
            if(result===200){
                alert("删除"+majorName+"成功！");
                window.location.href="/system/system_major_settings";
            } else {
                alert("删除"+majorName+"失败！");
            }
        });
    }

});

/**
 * species更新
 */
$('.btn_species_submit').on('click', function () {
    var speciesId = $(this).parent().parent().children().eq(0).val();
    var oldSpeciesName = $(this).parent().parent().children().eq(1).text();
    var newSpeciesName = $(this).parent().parent().children().eq(3).children().eq(0).val();

    if(confirm("是否将"+oldSpeciesName+"更名为"+newSpeciesName+"?")){

        $.post("/system/updateSpecies", {speciesId: speciesId,newSpeciesName: newSpeciesName},function (data){
            var result = JSON.parse(data['result']);
            if(result===200){
                alert("更新成功！");
                window.location.href="/system/system_basic";
            } else {
                alert("更新"+oldSpeciesName+"失败！");
            }
        });

    }

});

/**
 * grade 添加
 */

$('.btn_grade_add').on('click', function () {
    var grade = $(this).parent().parent().children().eq(1).children().eq(0).val();
    if(confirm("增加"+grade+"级")){
        $.post('/system/addGrade',{grade : grade},function (data) {

            var result = JSON.parse(data['result']);
            if (result === 200) {
                alert("添加成功！");
                window.location.href = "/system/system_basic";
            } else {
                alert("添加" + grade + "级失败");
            }
        });
    }

});

/**
 * grade 删除
 */
$('.btn_grade_delete').on('click', function () {
    var id = $(this).parent().parent().children().eq(0).val();
    var grade = $(this).parent().parent().children().eq(1).text();
    if(confirm("删除"+grade+"级")){
        $.post("/system/deleteGrade", {gradeId: id},function (data){
            var result = JSON.parse(data['result']);
            if(result===200){
                alert("删除成功！");
                window.location.href="/system/system_basic";
            } else {
                alert("删除"+grade+"级失败！");
            }
        });
        //$(this).parent().parent().remove();
    }
});

/**
 * position增加
 */
$('.btn_position_add').on('click', function () {
    var position = $(this).parent().parent().children().eq(1).children().eq(0).val();

    if(confirm("增加"+position+"职务？")){
        $.post('/system/addPosition',{position : position},function (data) {

            var result = JSON.parse(data['result']);
            if (result === 200) {
                alert("添加成功！");
                window.location.href = "/system/system_basic";
            } else {
                alert("添加" + position + "职务失败！");
            }
        });
    }

});

/**
 * 职务删除
 */
$('.btn_position_delete').on('click', function () {
    var id = $(this).parent().parent().children().eq(4).val();
    var position = $(this).parent().parent().children().eq(0).text();
    if(confirm("删除"+position+"职务？")){
        $.post("/system/deletePosition", {id: id},function (data){
            var result = JSON.parse(data['result']);
            if(result===200){
                alert("删除职务"+position+"成功！");
                window.location.href="/system/system_basic";
            } else {
                alert("删除职务"+position+"失败！");
            }
        });
    }
});

/**
 * 职务修改
 */

$('.btn_position_modify').on('click', function () {
    $(this).parent().addClass('panel_close');
    $(this).parent().parent().children().eq(0).addClass('panel_close');
    $(this).parent().parent().children().eq(2).removeClass('panel_close');
    $(this).parent().parent().children().eq(3).removeClass('panel_close');
});

/**
 *确认修改职务
 */
$('.btn_position_submit').on('click', function () {
    var id = $(this).parent().parent().children().eq(4).val();
    var newposition = $(this).parent().parent().children().eq(2).children().eq(0).val();
    var oldposition = $(this).parent().parent().children().eq(0).text();

    if(confirm("确认将"+oldposition+"更名为"+newposition+"?")){

        $.post("/system/updatePosition", {id: id,newposition: newposition},function (data){
            var result = JSON.parse(data['result']);
            if(result===200){
                alert("修改成功！");
                window.location.href="/system/system_basic";
            } else {
                alert("更新"+oldSpeciesName+"失败！");
            }
        });

    }
});

/**
 * 取消修改
 */
$('.btn_position_submitout').on('click', function () {
    $(this).parent().addClass('panel_close');
    $(this).parent().parent().children().eq(0).removeClass('panel_close');
    $(this).parent().parent().children().eq(1).removeClass('panel_close');
    $(this).parent().parent().children().eq(2).addClass('panel_close');
    // $(this).parent().parent().children().eq(0).addClass('panel_close');
    // $(this).parent().parent().children().eq(1).addClass('panel_close');
});

/**
 * 删除教师
 */
$('.btn_teacher_delete').on('click', function () {
    var id = $(this).parent().parent().children().eq(8).val();
    var teacher = $(this).parent().parent().children().eq(0).text();
    if(confirm("删除"+teacher+"管理员账号?")){
        $.post('/system/deleteTeacher', {id: id},function (data){
            var result = JSON.parse(data['result']);
            if(result===200){
                alert("成功删除用户"+teacher);
                window.location.href="/system/system_teacher_settings";
            } else {
                alert("删除用户"+teacher+"失败！");
            }
        });

    }
});

/**
 * 增加教师用户
 */
$('.btn_teacher_add').on('click', function () {
    var username = $(this).parent().parent().children().eq(1).children().eq(0).val();
    var number = $(this).parent().parent().children().eq(3).children().eq(0).val();
    var position = $("#position").val();

    if(confirm("增加教师（管理员）"+username+",学工号"+number+"?")){
        $.post('/system/addTeacher',{username : username, number : number, position:position},function (data) {
            var result = JSON.parse(data['result']);
            if (result === 200) {
                alert("添加成功！");
                window.location.href = "/system/system_teacher_settings";
            } else {
                alert("添加" + username + "管理员失败");
            }
        });
    }

});

/**
 * 修改教师用户信息
 */

$('.btn_teacher_modify').on('click', function () {
    $(this).parent().addClass('panel_close');
    $(this).parent().parent().children().eq(0).addClass('panel_close');
    $(this).parent().parent().children().eq(1).addClass('panel_close');
    $(this).parent().parent().children().eq(2).addClass('panel_close');
    $(this).parent().parent().children().eq(4).removeClass('panel_close');
    $(this).parent().parent().children().eq(5).removeClass('panel_close');
    $(this).parent().parent().children().eq(6).removeClass('panel_close');
    $(this).parent().parent().children().eq(7).removeClass('panel_close');
});

/**
 * 确认修改教师用户信息
 */

$('.btn_teacher_submit').on('click', function () {
    var id = $(this).parent().parent().children().eq(8).val();
    var username = $(this).parent().parent().children().eq(4).children().eq(0).val();
    var number = $(this).parent().parent().children().eq(5).children().eq(0).val();
    var position = $(this).parent().parent().children().eq(6).children().eq(0).val();

    alert(username);
    alert(number);

    if(confirm("请确认更改信息！")){

        $.post("/system/updateTeacher", {id: id,username: username,number: number,position: position},
            function (data){
                var result = JSON.parse(data['result']);
                if(result===200){
                    alert("修改成功！");
                    window.location.href="/system/system_teacher_settings";
                } else {
                    alert("更新"+oldSpeciesName+"失败！");
                }
            }
        );

    }
});

/**
 * 提交志愿
 * */
$('.btn_intentPlan_submit').on('click', function () {
    var first = $(this).parent().parent().parent().children().eq(0).children().eq(1).children().eq(0).val();
    var second = $(this).parent().parent().parent().children().eq(1).children().eq(1).children().eq(0).val();
    var third = $(this).parent().parent().parent().children().eq(2).children().eq(1).children().eq(0).val();
    if(first!="" && second!="" && third!=""){
        if(confirm("保存志愿？")){
            $.post('/student/saveIntent',{first: first,second: second,third: third},function (data) {
                var result = JSON.parse(data['result']);

                if(result===200){
                    alert("提交成功！");
                    window.location.href="/student/intent_fill";
                }else{
                    alert("填报志愿失败！");
                }
            });
        }
    } else {
        alert("请完成全部志愿的选择！");
    }

});


/**
 * 取消修改
 */
$('.btn_teacher_submitout').on('click', function () {
    $(this).parent().addClass('panel_close');
    $(this).parent().parent().children().eq(0).removeClass('panel_close');
    $(this).parent().parent().children().eq(1).removeClass('panel_close');
    $(this).parent().parent().children().eq(2).removeClass('panel_close');

    $(this).parent().parent().children().eq(3).removeClass('panel_close');
    $(this).parent().parent().children().eq(4).addClass('panel_close');
    $(this).parent().parent().children().eq(5).addClass('panel_close');
    $(this).parent().parent().children().eq(6).addClass('panel_close');
});

$('#stu_info_search').on('click', function () {
    $('.stu_info_search_panel').removeClass('panel_close');
    $('.stu_info_import_panel').addClass('panel_close');
    $('.table_panel').removeClass('panel_close');
});

$('#stu_info_import').on('click', function () {
    $('.stu_info_search_panel').addClass('panel_close');
    $('.stu_info_import_panel').removeClass('panel_close');
    $('.table_panel').removeClass('panel_close');
});

function major(major_name,class_plan_amount,stu_plan_amount){ //use factory
    this.major_name = major_name;
    this.class_plan_amount = class_plan_amount;
    this.stu_plan_amount = stu_plan_amount;
    return this;
};

$('#plan_save').on('click', function () {

    var json = [];
    var index = 0;
    var grade = $('#grades_select').val();
    var species = $('#species_select').val();
    var amount = $('#amount_species').val();

    if(grade!="" && species!="" && amount!=""){
        $(".div-append").each(function(){

            var major_name = $(this).children().eq(0).children().val();
            var class_plan_amount = $(this).children().eq(1).children().val();
            var stu_plan_amount = $(this).children().eq(2).children().val();

            json[index] = new major(major_name,class_plan_amount,stu_plan_amount);
            index++;
        });

        var json_majors = JSON.stringify(json);

        if(confirm("确认提交！")){
            $.post("/teacher/savePlan", {grade: grade,species: species,amount: amount,json_majors:json_majors}, function (data) {
                var result = JSON.parse(data['result']);
                if(result===200){
                    alert("保存成功！");
                    window.location.href="/teacher/plan_settings";
                } else {
                    alert("创建计划失败！");
                }
            });
        }
    } else {
        alert("请填写完整再提交！");
    }

});

$('#plan_save_modify').on('click', function () {

    var json = [];
    var index = 0;
    var grade = $('#grades_select_modify').val();
    var species = $('#species_select_modify').val();
    var amount = $('#amount_species_modify').val();

    if(grade!="" && species!="" && amount!=""){
        $(".div-append-modify").each(function(){

            var major_name = $(this).children().eq(0).children().val();
            var class_plan_amount = $(this).children().eq(1).children().val();
            var stu_plan_amount = $(this).children().eq(2).children().val();

            json[index] = new major(major_name,class_plan_amount,stu_plan_amount);
            index++;
        });

        var json_majors = JSON.stringify(json);

        if(confirm("确认提交！")){
            $.post("/teacher/savePlan", {grade: grade,species: species,amount: amount,json_majors:json_majors}, function (data) {
                var result = JSON.parse(data['result']);
                if(result===200){
                    alert("保存成功！");
                    window.location.href="/teacher/plan_settings";
                } else {
                    alert("保存计划失败！");
                }
            });
        }
    }else {
        alert("请填写完整再提交！");
    }

});

$('#btn_plan_create_show').on('click', function () {

    if($(this).text()=='新建计划'){
        $(this).text('取消');
    } else {
        $(this).text('新建计划');
        $(".div-append").remove();
    }

    $('#plan_create_panel').toggleClass('panel_close');
});

$("#plan_submitout").on('click', function () {
    $(this).parent().parent().parent().addClass('panel_close');
    if($("#btn_plan_create_show").text()=='新建计划'){
        $("#btn_plan_create_show").text('取消');
    } else {
        $("#btn_plan_create_show").text('新建计划');
        $(".div-append").remove();
    }
});

$("#plan_submitout_modify").on('click', function () {
    $(this).parent().parent().parent().addClass('panel_close');
    $(".div-append-modify").remove();
});

$(".btn_plan_release").on('click', function(){
    var tbody = $(this).parent().parent().children("table").children("tbody");
    var species = tbody.children().eq(0).children();
    var planId = species.children().eq(0).val();
    var status = species.children().eq(1).val();
    if(confirm("是否确认发布！")) {
        $.post("/teacher/changePlanStatus", {planId: planId, status: status}, function (data) {
            if (data.result === 200) {
                alert("发布成功！");
                window.location.href = "/teacher/plan_settings";
            } else {
                alert("发布失败！！");
            }
        });
    }
});

$(".btn_plan_revocation_release").on('click', function(){
    var tbody = $(this).parent().parent().children("table").children("tbody");
    var species = tbody.children().eq(0).children();
    var planId = species.children().eq(0).val();
    var status = species.children().eq(1).val();
    if(confirm("是否确认撤销发布！")) {
        $.post("/teacher/changePlanStatus", {planId: planId, status: status}, function (data) {
            if (data.result === 200) {
                alert("撤销成功！");
                window.location.href = "/teacher/plan_settings";
            } else {
                alert("撤销失败！！");
            }
        });
    }
});

$(".btn_plan_delete").on('click', function(){
    var tbody = $(this).parent().parent().children("table").children("tbody");
    var species = tbody.children().eq(0).children();
    var planId = species.children().eq(0).val();
    if(confirm("请慎重删除！")) {
        $.post("/teacher/deletePlan", {planId: planId}, function (data) {
            if (data.result === 200) {
                alert("删除成功！");
                window.location.href = "/teacher/plan_settings";
            } else {
                alert("删除失败！！");
            }
        });
    }
});

$(".btn_plan_execute").on('click', function () {

    var json = [];

    var json_majors = JSON.stringify(json);
    if(confirm("确认执行！")){
        $.post("/teacher/execute", {json_majors:json_majors}, function (data) {
            var result = JSON.parse(data['result']);
            if(result===200){
                alert("保存成功！");
                window.location.href="/teacher/plan_settings";
            } else {
                alert("保存计划失败！");
            }
        });
    }

});


$("#import_form").submit(function(e){
    var grade = $(this).children().children().eq(1).children().val();
    var species = $(this).children().children().eq(3).children().val();
    var message = "";
    if(grade == "" ){
        message = message + "年级不能为空" + "\n";
    }
    if(species == "" ){
        message = message + "专业大类不能为空" + "\n";
    }
    if(message == ""){
        alert("数据导入成功，请等待到页面刷新再行操作！");
        return true;
    } else {
        alert(message);
        return false;
    }


});

$(".checkbox-all").change(function () {
    if($(".checkbox-all").attr("checked")){
        //alert("选中");
        $($(".checkbox-simple").attr("checked",true));
    } else {
        $($(".checkbox-simple").attr("checked",false));
    }

});

/**
 * 分流计划条件查询
 */
$(".btn_plan_search").on('click', function () {
     var theForm = $(this).parent().parent();
     var grade = theForm.children().eq(0).children().eq(1).children("select").val();
     var term = theForm.children().eq(1).children().eq(1).children("select").val();
     var species = theForm.children().eq(2).children().eq(1).children("select").val()
     var status = theForm.children().eq(3).children().eq(1).children("select").val();
     status = (status == "" ? 3 : (status == "发布中" ? 1 : 0));
     //alert(status);
     //alert(grade+" "+term+" "+species+" "+status);
     console.log(grade);
     $("table").find("tbody").each(function (i,item){
        if(!$(this).parent().parent().hasClass("panel_close")){
            $(this).parent().parent().toggleClass("panel_close");
        }
        var grade_judge = $(this).children().children().children().eq(3);
        var term_judge = $(this).children().children().children().eq(0);
        var species_judge = $(this).children().children().children().eq(4);
        var status_judge = $(this).children().children().children().eq(1);

        if((grade!="" ? grade==grade_judge.text() : true) && (species!="" ? species==species_judge.text() : true) &&
        (status!=3 ? status==status_judge.val() : true)){
           $(this).parent().parent().toggleClass("panel_close");
        }

     });
});

/**
 * 分流计划展示全部
 */
$(".btn_plan_search_all").on('click', function () {
     $("table").find("tbody").each(function (){
        if($(this).parent().parent().hasClass("panel_close")){
            $(this).parent().parent().toggleClass("panel_close");
        }
     });
});


