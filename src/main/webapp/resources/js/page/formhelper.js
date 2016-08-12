(function($) { // 这里将多选的值封装为一个数值来进行处理。如果使用的时候需要将多选的值封装为“,”连接的字符串或者其他形式，请自行修改相应代码。
    // 开始时间格式应为yyyy-MM-dd HH:mm
    var _reTimeReg = /^(?:19|20)[0-9][0-9]-(?:(?:0[1-9])|(?:1[0-2]))-(?:(?:[0-2][1-9])|(?:[1-3][0-1])) (?:(?:[0-2][0-3])|(?:[0-1][0-9])):[0-5][0-9]$/;

    $.fn.serializeJson = function() {
        var serializeObj = {};
        var array = this.serializeArray();
        // var str = this.serialize();
        $(array).each(function() {
            if (this.value && _reTimeReg.test(this.value)) {
                if (this.value.length == 16) {
                    this.value += ':00';
                }
            }
            if (serializeObj[this.name]) {
                if ($.isArray(serializeObj[this.name])) {
                    serializeObj[this.name].push(this.value);
                } else {
                    serializeObj[this.name] = [serializeObj[this.name], this.value];
                }
            } else {
                serializeObj[this.name] = this.value;
            }
        });
        return serializeObj;
    };
})(jQuery);

// Ajax的方式提交表单
function ajaxSubmit(form, callback) {
    var element = $(form).find(":button");
    $(form).ajaxSubmit({
        beforeSubmit: function(result) { // pre-submit
            element.attr("disabled", true);
        },
        complete : function(xhr, ts) {
			element.removeAttr("disabled");
		},
        success: function(result) { // post-submit callback
        	if (result != null && result.code === 0) {
				var msg = result.msg ? result.msg : '操作成功！';
						if(callback) {
							callback(result.data);
						}
			} else {
				//var msg = "操作出错：" + (result.msg ? result.msg : "exception");
				 var msg=result.data;
				warningAlert(msg);
			}
        },
        error: function(xmlHttpRequest, textStatus, thrownError) {
        	var msg = "请求失败！";
        	warningAlert(msg);
        },
        /** other available options: **/
        // target: '#output1' // target element(s) to be updated with server response
        // url: url // override for form's 'action' attribute
        // type: type // 'get' or 'post', override for form's 'method' attribute
        // dataType: null // 'xml', 'script', or 'json'  (expected server response type)
        // clearForm: true // clear all form fields after successful submit
        // resetForm: true // reset the form after successful submit
        // buttons:	null(jQuery对象) 当你触发了ajax校验，buttons里对应的按钮（组）就会灰掉，一直等待服务器返回数据为止
        /** $.ajax options can be used here too, for example: **/
        //timeout: 20000
        timeout:0
    });
}

//Ajax的方式提交表单
function ajaxSubmitZDlg(form) {
    $(form).ajaxSubmit({
        beforeSubmit: function(result) { // pre-submit
        	$("#zhongxin").hide();
	    	$("#zhongxin2").show();
        },
        complete : function(xhr, ts) {
		},
        success: function(result) { // post-submit callback
        	if (result != null && result.code === 0) {
				var msg = result.msg ? result.msg : '操作成功！';
				if (bootbox) {
					bootbox.alert(result.msg, function() {
						top.Dialog.close();
					});
				} else {
					alert(msg);
					top.Dialog.close();
				}
			} else {
				var msg = "操作出错：" + (result.msg ? result.msg : "exception");
				if (bootbox) {
					bootbox.alert(msg);
				} else {
					alert(msg);
				}
				$("#zhongxin2").hide();
		    	$("#zhongxin").show();
			}
        },
        error: function(xmlHttpRequest, textStatus, thrownError) {
        	var msg = "请求失败！";
			if (bootbox) {
				bootbox.alert(msg);
			} else {
				alert(msg);
			}
			$("#zhongxin2").hide();
	    	$("#zhongxin").show();
        },
        //timeout: 20000
         timeout:0
    });
}

if ($.validator) {

    //比较日期大小, yyyy-MM-dd
    $.validator.methods.compareDate = function(value, element, param) {
        if (!$(param).val() || !value) {
            return false;
        } else {
            var startDate = new Date($(param).val().replace(new RegExp("-", 'g'), "/"));
            var endDate = new Date(value.replace(new RegExp("-", 'g'), "/"));
            return startDate <= endDate;
        }
    };

    //比较时间大小, HH:mm
    $.validator.methods.compareTime = function(value, element, param) {
        if (!$(param).val() || !value) {
            return false;
        } else {
            var startDate = new Date("2015/1/1 " + $(param).val() + ":00");
            var endDate = new Date("2015/1/1 " + value + ":00");
            return startDate <= endDate;
        }
    };

    // 比较两个日期是否都为空
    $.validator.methods.doubleEmpty = function(value, element, param) {
        if (!value && $(param).val()) {
            return false;
        } else {
            return true;
        }
    };

    // 验证两个对象中至少存在一个
    $.validator.methods.atLeastOne = function(value, element, param) {
        if (!$(param).val() && !value) {
            return false;
        } else {
            return true;
        }
    };
    
    // 验证两个对象中至少存在一个
    $.validator.methods.isIdCard = function(value, element, param) {
		if ($(param).val() == "身份证") {
			value = value.toUpperCase();
			return /(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(value);
		} else if ($(param).val() != "身份证") {
			return true;
		} else {
			return false;
		}
    };
    
    jQuery.validator.addMethod("isZhName", function(value, element) {
        return this.optional(element) || /^[\u4e00-\u9fa5]{2,10}$/.test(value);
    },
    "只能包括2~10个中文字。");

    jQuery.validator.addMethod("isPhone", function(value, element) {
        return this.optional(element) || (value.length == 11 && /^(((13[0-9]{1})(15[0-9]{1}))+\d{8})$/.test(value) || /^(\d{3,4}-?)?\d{7,9}$/g.test(value));
    },
    "请输入一个有效的电话号码。");
    
    jQuery.validator.addMethod("isTruecard", function(value, element) {
        return this.optional(element) || /(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(value);
    },
    "请输入一个有效的身份证。");
    
}