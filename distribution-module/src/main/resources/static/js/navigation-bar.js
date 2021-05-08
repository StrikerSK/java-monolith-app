let hamburger;
let navMenu;
let navLink;

window.onload = function(){
    hamburger = document.querySelector(".hamburger");
    navMenu = document.querySelector(".nav-menu");
    navLink = document.querySelectorAll(".nav-link");

    hamburger.addEventListener("click", mobileMenu);
    navLink.forEach(n => n.addEventListener("click", closeMenu));
}



function mobileMenu() {
    hamburger.classList.toggle("active");
    navMenu.classList.toggle("active");
}

function closeMenu() {
    hamburger.classList.remove("active");
    navMenu.classList.remove("active");
}