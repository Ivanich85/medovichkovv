$(document).ready(function() {
    $(".btn-recipe").click(function () {
        document.location.href = "/recipe/";
    });

    $(".btn-ingredient").click(function () {
        document.location.href = "/ingredient/";
    });

    $(".btn-index").click(function () {
        document.location.href = "/";
    });

    $(".btn-new-ingredient").click(function () {
        document.location.href = "/ingredient/new";
    });

});