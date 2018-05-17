<input type="hidden" id="operateButton" name="operateButton" value='${operateButton!"" }' />

<script language="javascript" type="text/javascript">
    $(function () {
        var jsonStr = $("#operateButton").val();
        if("" != jsonStr && null != jsonStr){
	        var showButtonList = JSON.parse(jsonStr);
	        for (var i = 0; i < showButtonList.length; i++) {
	            var buttonID = showButtonList[i].id;
	            var onclick = showButtonList[i].onClientClick;
				$("#" + buttonID).attr("onclick",onclick);
				$("#" + buttonID).removeClass("disabled");
	        }
        }
    })
</script>
