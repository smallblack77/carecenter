var nav_open = false;

// 获取汉堡按钮
const burger = document.querySelector(".burger");
// 获取导航菜单
const navMenu = document.querySelector(
  ".nav-menu"
);

//获取菜单项
const navMenuItems = document.querySelectorAll(
  ".nav-menu li"
);

//注册监听
burger.addEventListener("click", () => {
  // 汉堡按钮
  burger.classList.toggle("active");
  // 导航菜单开关
  navMenu.classList.toggle("open");

  // 菜单项动画
  navMenuItems.forEach((item, index) => {
    // 如果已添加animation,先取消
    if (item.style.animation) {
      item.style.animation = "";
    } else {
      item.style.animation = `0.3s ease-in slideIn forwards ${index *
        0.1 +
        0.3}s`;
    }
  });
});

burger.addEventListener("click", () => {
  if (nav_open === false) {
    var nav_open = true;
    console.log(nav_open);
    // 汉堡按钮
  burger.classList.toggle("active");
  // 导航菜单开关
  navMenu.classList.toggle("open");

  // 菜单项动画
  navMenuItems.forEach((item, index) => {
    // 如果已添加animation,先取消
    // if (item.style.animation) {
    //   item.style.animation = "";
    // } else {
      item.style.animation = `0.3s ease-in slideIn forwards ${index *
        0.1 +
        0.3}s`;
    // }
  });
  return;
  } else {
    var nav_open = false
    console.log(open);
       // 汉堡按钮
       burger.classList.remove("active");
      burger.classList.toggle("c-active");
  // 导航菜单开关
  navMenu.classList.toggle("open");
  navMenu.classList.toggle("close");

  // 菜单项动画
  navMenuItems.forEach((item, index) => {
    // 如果已添加animation,先取消
    if (item.style.animation) {
      item.style.animation = "";
    } else {
      item.style.animation = `0.3s ease-in slideOut forwards ${index *
        0.1 +
        0.3}s`;
    }
  });
  }
  return
  
});
