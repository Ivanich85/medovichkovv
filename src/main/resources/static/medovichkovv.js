$(document).ready(function() {

    $(".btn-index").click(function () {
        document.location.href = "/";
    });

    // Рецепт
    $(".btn-recipe").click(function () {
        document.location.href = "/recipe/";
    });

    $(".btn-current-recipe").click(function () {
        document.location.href = "/recipe/" + $("#recipe-id").val();
    });

    $(".btn-update-reсipe").click(function () {
        document.location.href = "/recipe/update/" + $("#recipe-id").val();
    });

    $(".btn-recipe-new").click(function () {
        document.location.href = "/recipe/new";
    });

    // Компонент
    $(".btn-new-component").click(function () {
        document.location.href = "/component/new?recipeId=" + $("#recipe-id").val();
    });

    // Общие ингредиенты
    $(".btn-ingredient").click(function () {
        document.location.href = "/ingredient/";
    });

    $(".btn-new-ingredient").click(function () {
        document.location.href = "/ingredient/new";
    });

});

function goTo(url) {
    document.location.href = url;
}