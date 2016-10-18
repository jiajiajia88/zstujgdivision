$('#btn_phone_modify').on('click', function () {
    $('#phone_number_modify').removeClass('panel_close');
    $('#phone_number_label').addClass('panel_close');
    $('#btn_phone_modify').addClass('panel_close');
    $('#btn_phone_submit').removeClass('panel_close');
    $('#btn_phone_cancel').removeClass('panel_close');
});

$('#btn_phone_cancel').on('click', function () {
    $('#phone_number_modify').addClass('panel_close');
    $('#phone_number_label').removeClass('panel_close');
    $('#btn_phone_modify').removeClass('panel_close');
    $('#btn_phone_submit').addClass('panel_close');
    $('#btn_phone_cancel').addClass('panel_close');
});

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

$('.btn_species_modify').on('click', function () {
    $(this).parent().addClass('panel_close');
    $(this).parent().parent().children().eq(0).addClass('panel_close');
    $(this).parent().parent().children().eq(2).removeClass('panel_close');
    $(this).parent().parent().children().eq(3).removeClass('panel_close');
});

$('.btn_species_delete').on('click', function () {
    var species = $(this).parent().parent().children().eq(0).text();
    if(confirm("删除"+species+"大类")){
        $(this).parent().parent().remove();
    }
});

$('.btn_grade_delete,.btn_species_delete').on('click', function () {
    var grade = $(this).parent().parent().children().eq(0).text();
    if(confirm("删除"+grade+"学年")){
        $(this).parent().parent().remove();
    }
});

$('.btn_position_delete').on('click', function () {
    var position = $(this).parent().parent().children().eq(0).text();
    if(confirm("删除"+position+"职务")){
        $(this).parent().parent().remove();
    }
});

$('.btn_position_modify').on('click', function () {
    $(this).parent().addClass('panel_close');
    $(this).parent().parent().children().eq(0).addClass('panel_close');
    $(this).parent().parent().children().eq(2).removeClass('panel_close');
    $(this).parent().parent().children().eq(3).removeClass('panel_close');
});

$('.btn_manager_delete').on('click', function () {
    var manager = $(this).parent().parent().children().eq(0).text();
    if(confirm("删除"+manager+"管理员账号")){
        $(this).parent().parent().remove();
    }
});

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

