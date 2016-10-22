/*修改电话*/
$('#btn_phone_modify').on('click', function () {
    $('#phone_number_modify').removeClass('panel_close');
    $('#phone_number_label').addClass('panel_close');
    $('#btn_phone_modify').addClass('panel_close');
    $('#btn_phone_submit').removeClass('panel_close');
    $('#btn_phone_cancel').removeClass('panel_close');
});

/*取消更新电话号码*/
$('#btn_phone_cancel').on('click', function () {
    $('#phone_number_modify').addClass('panel_close');
    $('#phone_number_label').removeClass('panel_close');
    $('#btn_phone_modify').removeClass('panel_close');
    $('#btn_phone_submit').addClass('panel_close');
    $('#btn_phone_cancel').addClass('panel_close');
});

/*提交更新电话号码*/
$('#btn_phone_submit').on('click', function () {
    var phone_number_modify = $('#phone_number_modify').val();
    $.post("/updatePhone", {phoneNumber: phone_number_modify},function (data){
        var result = JSON.parse(data['result']);
        if(result===200){
            window.location.href="/account";
        } else {
            alert("电话号码失败！");
        }
    });
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
        $.post('/addSpecies',{species : species},function (data) {
            var result = JSON.parse(data['result']);
            if (result === 200) {
                window.location.href = "/system_basic";
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
        $.post("/deleteSpecies", {speciesId: speciesId},function (data){
            var result = JSON.parse(data['result']);
            if(result===200){
                window.location.href="/system_basic";
            } else {
                alert("删除"+speciesName+"失败！");
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

        $.post("/updateSpecies", {speciesId: speciesId,newSpeciesName: newSpeciesName},function (data){
            var result = JSON.parse(data['result']);
            if(result===200){
                window.location.href="/system_basic";
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
        $.post('/addMajor',{major_name :major_name, major_species: major_species},function (data) {
            var result = JSON.parse(data['result']);
            if (result === 200) {
                alert("添加"+major_name+"成功！");
                window.location.href = "/system_major_settings";
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
        $.post("/deleteMajor", {majorId: majorId},function (data){
            var result = JSON.parse(data['result']);
            if(result===200){
                alert("删除"+majorName+"成功！");
                window.location.href="/system_major_settings";
            } else {
                alert("删除"+majorName+"失败！");
            }
        });
    }

});

/**
 * grade 添加
 */

$('.btn_grade_add').on('click', function () {
    var grade = $(this).parent().parent().children().eq(1).children().eq(0).val();
    if(confirm("增加"+grade+"学年")){
        $.post('/addGrade',{grade : grade},function (data) {

            var result = JSON.parse(data['result']);
            if (result === 200) {
                window.location.href = "/system_basic";
            } else {
                alert("添加" + grade + "年级失败");
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
    if(confirm("删除"+grade+"学年")){
        $.post("/deleteGrade", {gradeId: id},function (data){
            var result = JSON.parse(data['result']);
            if(result===200){
                window.location.href="/system_basic";
            } else {
                alert("删除"+grade+"年级失败！");
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
        $.post('/addPosition',{position : position},function (data) {

            var result = JSON.parse(data['result']);
            if (result === 200) {
                window.location.href = "/system_basic";
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
        $.post("/deletePosition", {id: id},function (data){
            var result = JSON.parse(data['result']);
            if(result===200){
                window.location.href="/system_basic";
            } else {
                alert("删除"+grade+"年级失败！");
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

        $.post("/updatePosition", {id: id,newposition: newposition},function (data){
            var result = JSON.parse(data['result']);
            if(result===200){
                window.location.href="/system_basic";
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
 * 删除管理员
 */
$('.btn_manager_delete').on('click', function () {
    var id = $(this).parent().parent().children().eq(8).val();
    var manager = $(this).parent().parent().children().eq(0).text();
    if(confirm("删除"+manager+"管理员账号?")){
        $.post("/deleteManager", {id: id, manager :manager},function (data){
            var result = JSON.parse(data['result']);
            if(result===200){
                window.location.href="/system_teacher_settings";
            } else {
                alert("删除"+speciesName+"失败！");
            }
        });

    }
});

/**
 * 增加管理员
 */
$('.btn_manager_add').on('click', function () {
    var username = $(this).parent().parent().children().eq(1).children().eq(0).val();
    var number = $(this).parent().parent().children().eq(3).children().eq(0).val();

    if(confirm("增加教师（管理员）"+username+",学工号"+number+"?")){
        $.post('/addManager',{username : username, number : number},function (data) {
            var result = JSON.parse(data['result']);
            if (result === 200) {
                window.location.href = "/system_teacher_settings";
            } else {
                alert("添加" + username + "管理员失败");
            }
        });
    }

});

/**
 * 修改管理员信息
 */

$('.btn_manager_modify').on('click', function () {
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
 * 确认修改管理员信息
 */

$('.btn_manager_submit').on('click', function () {
    var id = $(this).parent().parent().children().eq(8).val();
    var username = $(this).parent().parent().children().eq(4).children().eq(0).val();
    var number = $(this).parent().parent().children().eq(5).children().eq(0).val();
    var position = $(this).parent().parent().children().eq(6).children().eq(0).val();

    if(confirm("请确认更改信息！")){

        $.post("/updateManager", {id: id,username: username,number: number,position: position},function (data){
            var result = JSON.parse(data['result']);
            if(result===200){
                window.location.href="/system_teacher_settings";
            } else {
                alert("更新"+oldSpeciesName+"失败！");
            }
        });

    }
});

/**
 * 提交志愿
 * */
$('.btn_intentPlan_submit').on('click', function () {
    var first = $(this).parent().parent().parent().children().eq(0).children().eq(1).children().eq(0).val();
    var second = $(this).parent().parent().parent().children().eq(1).children().eq(1).children().eq(0).val();
    var third = $(this).parent().parent().parent().children().eq(2).children().eq(1).children().eq(0).val();
    if(confirm("保存志愿？")){
        $.post('/student/saveIntent',{first: first,second: second,third: third},function (data) {
            var result = JSON.parse(data['result']);

            if(result===200){
                window.location.href="/student/intent_fill";
            }else{
                alert("填报志愿失败！");
            }
        });
    }
});


/**
 * 取消修改
 */
$('.btn_manager_submitout').on('click', function () {
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
}

$('#plan_save').on('click', function () {

    var json = [];
    var index = 0;
    var grade = $('#grades_select').val();
    var species = $('#species_select').val();
    var amount = $('#amount_species').val();

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

});

$('#btn_plan_create_show').on('click', function () {

    if($(this).text()=='新建计划'){
        $(this).text('取消');
    } else {
        $(this).text('新建计划');
    }

    $('#plan_create_panel').toggleClass('panel_close');
});

