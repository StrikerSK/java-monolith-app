const makeResponsive = () => {
    const newClass = "responsive";
    let topNav = $('#myTopnav');

    if (topNav.hasClass(newClass)) {
        topNav.removeClass(newClass);
    } else {
        topNav.addClass(newClass);
    }
}
