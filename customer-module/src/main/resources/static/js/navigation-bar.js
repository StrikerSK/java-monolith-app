function makeResponsive() {
    let topNav = document.getElementById("myTopnav");
    if (topNav.className === "navbar") {
        topNav.className += " responsive";
    } else {
        topNav.className = "navbar";
    }
}
