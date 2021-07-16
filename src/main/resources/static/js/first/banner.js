// 轮播图方法
/*
	获取 必须知道的 变量
	步骤1: 不考虑过渡效果 直接 刷刷刷的 切换
		定时器中 index++
			    判断是否越界
			   修改 轮播图ul的 位置
			   考虑到 索引从1开始
			   css 默认 让ul 往左边窜一个屏幕宽度

	步骤2:	  下方的 索引li标签 修改 外观
		由于我们是使用.current 标示当前的索引值
		清空所有li的 class
		为当前的那个 li 添加current

	步骤3:然切换有动画效果
		使用css3中的transition
		.style.transition ='all .3s';
		在获取的时候 进行添加即可

	步骤4:当我切换到 最后一张时 瞬间 切到 第一张
		关闭过度
		瞬间切换到第一张

	步骤5:对代码 进行重构 添加进来了 过渡结束知识点
		由于 我们在修改 ul的位置时 会使用过度
		当注册了 过渡结束事件之后,每次 过渡完毕 都会 调用该事件
			将 判断 index  是否 越界 以及 修改 索引的 代码 全部 迁移到 过渡结束事件中

			定时器逻辑
				index++;

				修改 ul的 位置 ->开始过渡

			过渡结束事件逻辑
				判断 index是否有效
					进行修正
				修改索引li标签的 显示


*/

function banner() {

	//1 获取变量
	// 屏幕的宽度
	var width = document.querySelector('.train_banner').offsetWidth;
  console.log('train_banner' + width);
	// console.log(width);\
	//  获取 轮播图的ul
	var moveUl = document.querySelector('.banner_images');
	//统一高度，宽高比例可根据ui修改，目前为2.75
	// document.querySelector(".banner_images li img").style.height = width/2.75 + "px"
	var linum = 0
	for(var j=1;j<=document.querySelectorAll(".banner_images li").length-1;j++){
			document.querySelector(".banner_index-frame").appendChild(document.createElement("li"));
			// document.querySelectorAll(".banner_images li img")[j].style.height = width/2.75 + "px"
			linum++
		}
	// 索引的li标签
	var indexLiArr = document.querySelectorAll('.banner_index li');
	
	// 定义 index 记录 当前的 索引值
	// 默认 我们的ul 已经 往左边 移动了 一倍的宽度
	var Ul = document.querySelector('.train_banner');
	// (为什么 一位 最左边的图片 是用来做无限轮播的 不希望用户看到) 所以 index =1
	var left = document.querySelector('.train_banner_left');
	var right = document.querySelector('.train_banner_right');
	var index = 0;

	// 抽取的代码 提升代码的可读性,以及 降低维护的难度
	var startTransition = function() {
		moveUl.style.transition = 'all .5s';
	}

	var endTransition = function() {
		moveUl.style.transition = '';
	}

	// 由于 移动的距离 无法确定 所以提取为参数
	var setTransform = function(distance) {
		moveUl.style.transform = 'translateX(' + distance + 'px)';
	}

	// 开启定时器
	var timeId = setInterval(function() {
		// 累加
		index++;
		if(index >= document.querySelectorAll(".banner_images li").length) {
						index = 0
					}
		// 将 过渡开启 管你三七二十一 只要进来 就开启过渡 保证 过渡效果一直存在
		// moveUl.style.transition = 'all .3s';
		startTransition();

		// 修改 ul的位置
		// moveUl.style.transform = 'translateX('+index*width*-1+'px)';
		setTransform(index * width * -1);

	}, 3000);

	function li() {
		for(var i = 0; i < indexLiArr.length; i++) {
			indexLiArr[i].index = i;
			indexLiArr[i].onclick = function() {
				for(var i = 0; i < indexLiArr.length; i++) {
					indexLiArr[i].className = '';
				}
				indexLiArr[this.index].className = 'current';
				clearInterval(timeId);
				endTransition();
				index = this.index;
				setTransform((this.index) * width * -1);
				startTransition()

				timeId = setInterval(function() {
					// 累加

					index++;
					if(index >= document.querySelectorAll(".banner_images li").length) {
						index = 0
					}
					// 将 过渡开启 管你三七二十一 只要进来 就开启过渡 保证 过渡效果一直存在
					// moveUl.style.transition = 'all .5s';
					startTransition();

					// 修改 ul的位置
					// moveUl.style.transform = 'translateX('+index*width*-1+'px)';
					setTransform(index * width * -1);

				}, 3000)
			};

		}
	}
	li();

	//轮播左点击
	left.addEventListener('click', function() {
		clearInterval(timeId);
		startTransition();
		index--;
		if(index < 0) {
			// 跳到倒数第二张
			
			index = document.querySelectorAll(".banner_images li").length-1;
			// 关闭过渡
			// moveUl.style.transition = '';

			// 瞬间 修改一下 ul 的位置
			// moveUl.style.transform = 'translateX('+index*width*-1+'px)';
			setTransform(index * width * -1);
		} else {
			setTransform(index * width * -1);
		}

		// 修改 索引li标签的 class
		for(var i = 0; i < indexLiArr.length; i++) {
			indexLiArr[i].className = '';
		}

		// 有一个 1的 差值
		indexLiArr[index].className = 'current';

		timeId = setInterval(function() {
			// 累加
			
			index++;
			if(index >= document.querySelectorAll(".banner_images li").length) {
						index = 0
					}
			// 将 过渡开启 管你三七二十一 只要进来 就开启过渡 保证 过渡效果一直存在
			// moveUl.style.transition = 'all .3s';
			startTransition();

			// 修改 ul的位置
			// moveUl.style.transform = 'translateX('+index*width*-1+'px)';
			setTransform(index * width * -1);

		}, 3000)

	})

	//轮播右点击
	right.addEventListener('click', function() {
		clearInterval(timeId);
		startTransition();
		index++;
		if(index >= document.querySelectorAll(".banner_images li").length) {
			// 跳到倒数第二张
			index = 0;

			// 关闭过渡
			// moveUl.style.transition = '';

			// 瞬间 修改一下 ul 的位置
			// moveUl.style.transform = 'translateX('+index*width*-1+'px)';
			setTransform(index * width * -1);
		} else{
			setTransform(index * width * -1);
		}

		// 修改 索引li标签的 class
		for(var i = 0; i < indexLiArr.length; i++) {
			indexLiArr[i].className = '';
		}

		// 有一个 1的 差值
		indexLiArr[index].className = 'current';

		timeId = setInterval(function() {
			// 累加

			index++;
			if(index >= document.querySelectorAll(".banner_images li").length) {
						index = 0
					}
			// 将 过渡开启 管你三七二十一 只要进来 就开启过渡 保证 过渡效果一直存在
			// moveUl.style.transition = 'all .3s';
			startTransition();

			// 修改 ul的位置
			// moveUl.style.transform = 'translateX('+index*width*-1+'px)';
			setTransform(index * width * -1);

		}, 3000)

	})

	// 过渡 结束事件 用来 修正 index的值 并修改索引
	moveUl.addEventListener('webkitTransitionEnd', function() {

		//  如果 index 太大了 
		if(index >= document.querySelectorAll(".banner_images li").length) {
			index = 0;

			// 关闭过渡
			// moveUl.style.transition = '';
			endTransition();

			// 瞬间 修改一下 ul 的位置
			// moveUl.style.transform = 'translateX('+index*width*-1+'px)';
			setTransform(index * width * -1);
		} else if(index < 0) {
			// 跳到倒数第二张
			index = document.querySelectorAll(".banner_images li").length-1;

			// 关闭过渡
			// moveUl.style.transition = '';
			endTransition();

			// 瞬间 修改一下 ul 的位置
			// moveUl.style.transform = 'translateX('+index*width*-1+'px)';
			setTransform(index * width * -1);
		}

		// 修改 索引li标签的 class
		for(var i = 0; i < indexLiArr.length; i++) {
			indexLiArr[i].className = '';
		}

		// 有一个 1的 差值
		indexLiArr[index].className = 'current';
	})

	window.onresize = function() {
		endTransition();
		clearInterval(timeId);
		// width = document.documentElement.clientWidth;
    width = document.querySelector('.train_banner').offsetWidth;
		//图片统一高度
		// document.querySelector(".banner_images li img").style.height = 'width/2.75 + "px"'
    document.querySelector(".banner_images li img").style.height = 'auto'
		for(var j=1;j<=document.querySelectorAll(".banner_images li").length-1;j++){
			// document.querySelectorAll(".banner_images li img")[j].style.height = width/2.75 + "px"
			document.querySelectorAll(".banner_images li img")[j].style.height = 'auto'
		}
		setTransform(index * width * -1);
		timeId = setInterval(function() {
			// 累加
			index++;
			if(index >= document.querySelectorAll(".banner_images li").length) {
						index = 0
					}
			// 将 过渡开启 管你三七二十一 只要进来 就开启过渡 保证 过渡效果一直存在
			// moveUl.style.transition = 'all .3s';
			startTransition();
			
			// 修改 ul的位置
			// moveUl.style.transform = 'translateX('+index*width*-1+'px)';
			setTransform(index * width * -1);
		}, 3000)
	}
	var start = 0
	var tform = 0
	function handlerTouchEvent(event){
	    //只跟踪一次触摸
	    if(event.touches.length==1 || event.touches.length==0){//书上这里有错
	        switch(event.type){
	            case "touchstart":
	            	endTransition();
	            	clearInterval(timeId);
	              
	               start = event.touches[0].clientX
	                break;
	            case "touchend":
	            	if(start - event.changedTouches[0].clientX >= width/2){
	            		if(index >= document.querySelectorAll(".banner_images li").length-1){
	            			index = 0
	            		}else{
	            			index++
	            		}
	                	setTransform(index * width * -1);
	               }else{
	               		setTransform(index * width * -1);
	               }
	               
	            	if(event.changedTouches[0].clientX - start >= width/2){
	            		if(index <= 0){
	            			index = document.querySelectorAll(".banner_images li").length-1
	            		
	            		}else{
	            			index--
	            		}
	                	setTransform(index * width * -1);
	               }else{
	               		setTransform(index * width * -1);
	               }
	            	startTransition();
	               
	                timeId = setInterval(function() {
						// 累加
						index++;
						if(index >= document.querySelectorAll(".banner_images li").length) {
							index = 0
						}
						// 将 过渡开启 管你三七二十一 只要进来 就开启过渡 保证 过渡效果一直存在
						// moveUl.style.transition = 'all .3s';
						startTransition();
			
						// 修改 ul的位置
						// moveUl.style.transform = 'translateX('+index*width*-1+'px)';
						setTransform(index * width * -1);
					}, 5000)
	                break;
	            case "touchmove":
	                event.preventDefault(); //阻止滚动
	                tform = index * width * -1 - (start - event.changedTouches[0].clientX)
	                if(tform >= 0){
						tform = 0
					}
					
					if(tform <= -linum * width){
						tform = -linum * width
					}
					setTransform(tform);
	        }
	    }
	}
		
	moveUl.addEventListener('touchstart',handlerTouchEvent,false);
  moveUl.addEventListener('touchmove',handlerTouchEvent,false);
  moveUl.addEventListener('touchend',handlerTouchEvent,false);
}
