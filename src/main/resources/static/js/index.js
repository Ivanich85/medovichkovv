$(document).ready(function() {

    var recipesTableId = "#recipes_table";
    var myRecipesId = "#my_recipes";

    getRecipes();

    function getRecipes() {
        $.ajax({
            method: "GET",
            url: "/recipes",
            dataType: "json",
            success: function(responseJson) {
                showOrHideRecipes(responseJson);
            }
        })
    }

    function isEmptyJson(responseJson) {
        return responseJson.length === 0;
    }

    function showOrHideRecipes(responseJson) {
        if (isEmptyJson(responseJson)) {
            $(recipesTableId).hide();
            $(myRecipesId).html("Рецептов пока нет... :(");
        } else {
            $(recipesTableId).show();
            fillRecipes(responseJson);
            $(myRecipesId).html("Мои рецепты");
        }
    }

    function fillRecipes(responseJson) {
        $(recipesTableId).DataTable({
            data : responseJson,
            columns: [
                {
                    data: "id"
                },
                {
                    data: "name"
                },
                {
                    data: "favorite"
                }
            ]
        })
    }

});



