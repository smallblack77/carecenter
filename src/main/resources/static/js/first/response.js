var open = false;
			var navbar_visible = document.getElementById("visiable");
			function navVisi() {
				if (open == false) {
					open = true;
					navbar_visible.style.display = "block";
					navbar_visible.style.opacity = "1";
					setTimeout(function () {
						navbar_visible.style.top = "5.5rem";
					}, "0");
				} else {
					navbar_visible.style.top = "-21rem";
					navbar_visible.style.opacity = "0";
					setTimeout(function () {
						navbar_visible.style.display = "none";
						navbar_visible.style.opacity = "1";
					}, "500");
					open = false;
				}
	}


	// 滚回头部
	let arriveAni = false;
	var visiable = document.getElementById("toTop");
	console.log(visiable);
	window.addEventListener("scroll", function (e) {
		//alert("滚动了");
		//变量t就是滚动条滚动时，到顶部的距离
		// var scollHeight = document.documentElement.scrollTop;
		var scollHeight = window.pageYOffset;
		if (scollHeight <= 100) {
			arriveAni = true;
			if (arriveAni) {
				fadeout(visiable)
				// fadeout();
			}
		} else {
			arriveAni = false;
			if (!arriveAni) {
				fadein(visiable);
				// fadein()
			}
		}
	});
	function fadein(dom){
		dom.classList.remove("fadeOut");
		dom.classList.add("fadeIn");
		setTimeout(function () {
			dom.style.display = "flex";
		}, 500);
	};
	function fadeout(dom) {
		dom.classList.remove("fadeIn");
		dom.classList.add("fadeOut");
		setTimeout(function () {
			dom.style.display = "none";
		}, 500);
	}
	function backTop() {
		document.body.scrollTop = 0;
		document.documentElement.scrollTop = 0;
	}
	
	var success = document.getElementById("success");
	var warning = document.getElementById("warning");
	var success_text = document.getElementsByClassName("success-text");
	var warning_text = document.getElementsByClassName("warning-text");
	console.log(success_text[0]);

	function success_message_come(dom, msg) {
		success_text[0].innerHTML = msg;
		dom.style.display = "flex";
		dom.classList.remove("message-fadeOut");
		dom.classList.add("message-fadeIn");
		setTimeout(function () {
			success_disapear(dom);
		}, 2000);
	}
	function warning_message_come(dom, msg) {
		warning_text[0].innerText = msg;
		dom.style.display = "flex";
		dom.classList.remove("message-fadeOut");
		dom.classList.add("message-fadeIn");
		setTimeout(function () {
			warning_disapear(dom);
		}, 2000);
	}
	// function success_message_come() {
	// 	success.style.display = "flex";
	// 	success.classList.remove("message-fadeOut");
	// 	success.classList.add("message-fadeIn");
	// 	setTimeout(function () {
	// 		success_disapear();
	// 	}, 2000);
	// }
	// function warning_message_come() {
	// 	warning.style.display = "flex";
	// 	warning.classList.remove("message-fadeOut");
	// 	warning.classList.add("message-fadeIn");
	// 	setTimeout(function () {
	// 		warning_disapear();
	// 	}, 2000);
	// }
	function success_disapear(dom) {
		dom.classList.remove("message-fadeIn");
		dom.classList.add("message-fadeOut");
		setTimeout(function () {
			dom.style.display = "none";
		}, 500);
	}
	function warning_disapear(dom) {
		dom.classList.remove("message-fadeIn");
		dom.classList.add("message-fadeOut");
		setTimeout(function () {
			dom.style.display = "none";
		}, 500);
	}
	// function success_disapear() {
	// 	success.classList.remove("message-fadeIn");
	// 	success.classList.add("message-fadeOut");
	// 	setTimeout(function () {
	// 		success.style.display = "none";
	// 	}, 1500);
	// }
	// function warning_disapear() {
	// 	warning.classList.remove("message-fadeIn");
	// 	warning.classList.add("message-fadeOut");
	// 	setTimeout(function () {
	// 		warning.style.display = "none";
	// 	}, 1500);
	// }
