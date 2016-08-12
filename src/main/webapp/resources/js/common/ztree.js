function insertSelectedDw(id,text){
		var html = [];
		html.push('<div class="marginR10" style="display:inline-block"><small>'+text+';</small>');
		html.push('<input type="hidden" name="department" value='+id+' />');
		html.push('<button  class="close closethat">&times;</button></div>');
		return html;
	}
function insertSelectedDwtwo(id,text){
	var html = [];
	html.push('<div class="marginR10" style="display:inline-block"><small>'+text+';</small>');
	html.push('<input type="hidden" name="value"  value='+id+' />');
	html.push('<button  class="close closethat">&times;</button></div>');
	return html;
}
function insertSelectedDwzf(id,text){
	var html = [];
	html.push('<div class="marginR10" style="display:inline-block"><small>'+text+';</small>');
	html.push('<input type="hidden" name="organid"  value='+id+' />');
	html.push('<button  class="close closethat">&times;</button></div>');
	return html;
}
	function judgeExist(obj,id){
		var flag = false;
		var len = obj.length;
		if(len>0){
			for(var i=0;i<len;i++){
				if($(obj[i]).val() == id){
					flag= true;
					break;
				}
			}
		}
		return flag;
	}
function getSelectedNode(obj){
	return treeObj.getSelectedNodes();
}