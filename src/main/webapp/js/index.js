$(document).ready(function() {

    let recipesTableId = "#recipes_table";
    let myRecipesId = "#my_recipes";
    let recipesTable;

    getRecipes();

    function getRecipes() {
        $.ajax({
            method: "GET",
            url: "/all",
            dataType: "json",
            success: function(responseJson) {
                showOrHideRecipes(responseJson);
            }
        })
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

    function isEmptyJson(responseJson) {
        return responseJson.length === 0;
    }

    function fillRecipes(responseJson) {
        recipesTable = $(recipesTableId).DataTable({
            data : responseJson,
            createdRow: function(row, data) {
                $(row).attr("id", data.id)
            },
            columns: [
                {
                    data: "name"
                },
                {
                    data: "favorite"
                },
                {
                    data: "privacy_type"
                }
            ]
        })
    }

    $(recipesTableId).on("click", "tbody tr", function () {
        $("#recipe_id").modal({
            fadeDuration: 150,
            showClose: false,
            clickClose: false,
            escapeClose: false
        });
        let rowId = recipesTable.row(this).data().id;
        $.ajax({
            method: "GET",
            url: "/recipe/" + rowId,
            dataType: "json",
            success: function(responseJson) {
                console.log(responseJson);
                $("#recipe_description").html(responseJson.name);
            }
        })
    })

});



