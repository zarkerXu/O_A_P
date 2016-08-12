function lilist(page,totalPage,size){
	 var warningpagination = $('.center').find('.pagination');
     var allli = "";
     var prevli = "";
     if(page>totalPage&&page!=1){
      	page=totalPage;
         next(geturl(page,size));
      }
     if(totalPage==0){
    	 $("#bottomnav").hide();
     }else{
    	 $("#bottomnav").show();
     }
     if (page == 1) {
         prevli = "<li class=\"prevbutton disabled\"><a  href=\"#\">&laquo;</a></li>";
     } else {
         var prevpage = page - 1;
         prevli = "<li class=\"prevbutton nondisabled\"><a  href='#' onclick='next(\""+geturl(prevpage, size)+"\");'>&laquo;</a></li>";
     }
     //中间的可点击数字
     var pagelis = "";
     if(totalPage<11){
    	  for (var i = 1; i <= totalPage; i++) {
    	         var li = "";
    	         if (i == page) {
    	             li = "<li class=\"active page-" + i + "\"><a href=\"#\">" + i + "</a></li>";
    	         } else {
    	             li = "<li class=\"pagebutton page-" + i + "\"><a href='#' onclick='next(\""+geturl(i, size)+"\");'>" + i + "</a></li>";
    	         }
    	         
    	         pagelis += li;
    	     }
     }
     else{
    	 var middle=parseInt(totalPage/2);
    	var simpleli="<li class=\"pagebutton page-" + 1 + "\"><a href='#'>"+"···"+"</a></li>";
     for (var i = 1; i <= totalPage; i++) {
         var li = "";	
         //当前页数小于5
         if(page<5){
        	 if (i == page) {
                 li = "<li class=\"active page-" + i + "\"><a href=\"#\">" + i + "</a></li>";
             } else {
                 if(i>4&&i<totalPage){
                	 if(i==middle){
                		 li+=simpleli;
                		 }
                 }  
                 else{
                 li = "<li class=\"pagebutton page-" + i + "\"><a href='#' onclick='next(\""+geturl(i, size)+"\");'>" + i + "</a></li>";
                 } 
         }
         }
         //当前页数大于5小于最后-3
             else if(page>=5&&page<totalPage-3){
            	 if (i == page) {
            		 li+= "<li class=\"pagebutton page-" + i + "\"><a href='#' onclick='next(\""+geturl(i-1, size)+"\");'>" + (i-1)+ "</a></li>";
                     li+= "<li class=\"active page-" + i + "\"><a href=\"#\">" + i + "</a></li>";
                     li+= "<li class=\"pagebutton page-" + i + "\"><a href='#' onclick='next(\""+geturl(i+1, size)+"\");'>" + (i+1) + "</a></li>";
                     li+=simpleli;
                     li+="<li class=\"pagebutton page-" + i + "\"><a href='#' onclick='next(\""+geturl(totalPage, size)+"\");'>" + totalPage + "</a></li>";
                 } else {
            	 if(i<4){
                	 if(i==1){
                		 li = "<li class=\"pagebutton page-" + i + "\"><a href='#' onclick='next(\""+geturl(1, size)+"\");'>" + i + "</a></li>" ;
                		 li+=simpleli;}
                 }
                 }
             }
             else if(page>=totalPage-3){
            	 if(i==page){
            		 li+= "<li class=\"active page-" + i + "\"><a href=\"#\">" + i + "</a></li>";
            	 }
            	 else {
            		 if(i==1){
            			 li+="<li class=\"pagebutton page-" + i + "\"><a href='#' onclick='next(\""+geturl(1, size)+"\");'>" + i + "</a></li>" ;
                         li+=simpleli;
            		 }
                     if(i>=totalPage-3){   
                        li= "<li class=\"pagebutton page-" + i + "\"><a href='#' onclick='next(\""+geturl(i, size)+"\");'>" + i + "</a></li>";      
            	 }
             }
             }
         pagelis += li;
     }  
     } 
     var nextli = "";
     if (page == totalPage) {
         nextli = "<li class=\"nextbutton disabled\"><a href=\"#\">&raquo;</a></li>";
     } else {
         var nextpage = page + 1;
         nextli = "<li class=\"nextbutton nondisabled\"><a  href='#' onclick='next(\""+geturl(nextpage, size)+"\");'>&raquo;</a></li>";
     }
     $("#pages").val(page);
     allli = prevli + pagelis + nextli;
     warningpagination.html(allli);
}

function allSelect() {
	if ($(":checkbox").prop("checked")) {
		$(":checkbox").prop("checked", true);
	} else {
		$(":checkbox").prop("checked", false);
	}
}
function deleteData(){ 
	var inputs = $("#tablebody input:checkbox");
	var ids = "";
	for(var i=0;i<inputs.length;i++){
		if(inputs.eq(i).prop("checked")){
			ids+=inputs.eq(i).val()+",";
		}
		
	}
	return ids;
}
// time formart change
function times(date){
	 if(date==null){
		 return "";
	 }
	 var newdate=new Date(date);
	 var minutes=""+newdate.getMinutes();
	 var seconds=""+newdate.getSeconds();
	 if(minutes.length==1){
		 minutes="0"+minutes;
	 }
	 if(seconds.length==1){
		 seconds="0"+seconds;
	 } 
	
	var datetime=(newdate.getFullYear()+"/"+(newdate.getMonth()+1)+"/"+newdate.getDate()+" "+newdate.getHours()+":"+minutes+":"+seconds); 
	return datetime;
}
function successAlert(value) {
	var html='<div class="alert alert-success">';
	html+='<a href="#" class="close" data-dismiss="alert" aria-hidden="true">&times;</a>';
	html+=value;
	html+='</div>';
	$("#alert").html(html);
}
function warningAlert(value) {
	var html='<div class="alert alert-warning">';
	html+='<a href="#" class="close" data-dismiss="alert" aria-hidden="true">&times;</a>';
	html+=value;
	html+='</div>';
	$("#alert").html(html);
}

(function ($) {
	 
	  window.Ewin = function () {
	    var html = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
	               '<div class="modal-dialog modal-sm">' +
	                 '<div class="modal-content">' +
	                   '<div class="modal-header">' +
	                     '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>' +
	                     '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
	                   '</div>' +
	                   '<div class="modal-body">' +
	                   '<p>[Message]</p>' +
	                   '</div>' +
	                    '<div class="modal-footer">' +
	    '<button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>' +
	    '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>' +
	  '</div>' +
	                 '</div>' +
	               '</div>' +
	             '</div>';
	 
	 
	    var dialogdHtml = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
	               '<div class="modal-dialog">' +
	                 '<div class="modal-content">' +
	                   '<div class="modal-header">' +
	                     '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>' +
	                     '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
	                   '</div>' +
	                   '<div class="modal-body">' +
	                   '</div>' +
	                 '</div>' +
	               '</div>' +
	             '</div>';
	    var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
	    var generateId = function () {
	      var date = new Date();
	      return 'mdl' + date.valueOf();
	    }
	    var init = function (options) {
	      options = $.extend({}, {
	        title: "操作提示",
	        message: "提示内容",
	        btnok: "确定",
	        btncl: "取消",
	        width: 200,
	        auto: false
	      }, options || {});
	      var modalId = generateId();
	      var content = html.replace(reg, function (node, key) {
	        return {
	          Id: modalId,
	          Title: options.title,
	          Message: options.message,
	          BtnOk: options.btnok,
	          BtnCancel: options.btncl
	        }[key];
	      });
	      $('body').append(content);
	      $('#' + modalId).modal({
	        width: options.width,
	        backdrop: 'static'
	      });
	      $('#' + modalId).on('hide.bs.modal', function (e) {
	        $('body').find('#' + modalId).remove();
	      });
	      return modalId;
	    }
	 
	    return {
	      alert: function (options) {
	        if (typeof options == 'string') {
	          options = {
	            message: options
	          };
	        }
	        var id = init(options);
	        var modal = $('#' + id);
	        modal.find('.ok').removeClass('btn-success').addClass('btn-primary');
	        modal.find('.cancel').hide();
	 
	        return {
	          id: id,
	          on: function (callback) {
	            if (callback && callback instanceof Function) {
	              modal.find('.ok').click(function () { callback(true); });
	            }
	          },
	          hide: function (callback) {
	            if (callback && callback instanceof Function) {
	              modal.on('hide.bs.modal', function (e) {
	                callback(e);
	              });
	            }
	          }
	        };
	      },
	      confirm: function (options) {
	        var id = init(options);
	        var modal = $('#' + id);
	        modal.find('.ok').removeClass('btn-primary').addClass('btn-success');
	        modal.find('.cancel').show();
	        return {
	          id: id,
	          on: function (callback) {
	            if (callback && callback instanceof Function) {
	              modal.find('.ok').click(function () { callback(true); });
	              modal.find('.cancel').click(function () { callback(false); });
	            }
	          },
	          hide: function (callback) {
	            if (callback && callback instanceof Function) {
	              modal.on('hide.bs.modal', function (e) {
	                callback(e);
	              });
	            }
	          }
	        };
	      },
	      dialog: function (options) {
	        options = $.extend({}, {
	          title: 'title',
	          url: '',
	          width: 800,
	          height: 550,
	          onReady: function () { },
	          onShown: function (e) { }
	        }, options || {});
	        var modalId = generateId();
	 
	        var content = dialogdHtml.replace(reg, function (node, key) {
	          return {
	            Id: modalId,
	            Title: options.title
	          }[key];
	        });
	        $('body').append(content);
	        var target = $('#' + modalId);
	        target.find('.modal-body').load(options.url);
	        if (options.onReady())
	          options.onReady.call(target);
	        target.modal();
	        target.on('shown.bs.modal', function (e) {
	          if (options.onReady(e))
	            options.onReady.call(target, e);
	        });
	        target.on('hide.bs.modal', function (e) {
	          $('body').find(target).remove();
	        });
	      }
	    }
	  }();
	})(jQuery);