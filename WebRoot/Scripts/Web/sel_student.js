function getStudent(id_or_name)
{
    $.ajax({
        url: "/static/ret_val.json",
        type: "GET",
        dataType: "json",
        success: function(data) {
            var objname=document.getElementById("stdname");
            var objid=document.getElementById("stdid");
            objname.value=data.name
            objid.value=data.stdId
            var stdclass=document.getElementById("stdclass");
            if(stdclass!=null)
                stdclass.value=data.class

        }
    })
}
