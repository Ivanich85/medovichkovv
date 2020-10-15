DROP TABLE IF EXISTS MC_RECIPE_INGREDIENT;
DROP TABLE IF EXISTS MC_COMPONENT;
DROP TABLE IF EXISTS MC_RECIPE;
DROP TABLE IF EXISTS MC_INGREDIENT;

DROP SEQUENCE IF EXISTS MC_RECIPE_INGREDIENT_SEQ;
DROP SEQUENCE IF EXISTS MC_COMPONENT_SEQ;
DROP SEQUENCE IF EXISTS MC_RECIPE_SEQ;
DROP SEQUENCE IF EXISTS MC_INGREDIENT_SEQ;

CREATE TABLE MC_INGREDIENT (
                               ID              BIGINT          NOT NULL PRIMARY KEY ,
                               NAME            VARCHAR(127)    NOT NULL ,
                               QUANTITY        NUMERIC(6,2)    NOT NULL ,
                               QUANTITY_TYPE   VARCHAR(16)     NOT NULL ,
                               COST            NUMERIC(10,2)   NOT NULL
);
CREATE SEQUENCE MC_INGREDIENT_SEQ INCREMENT BY 1 MINVALUE 1000000;

COMMENT ON COLUMN MC_INGREDIENT.ID IS 'Row id';
COMMENT ON COLUMN MC_INGREDIENT.NAME IS 'Ingredient name';
COMMENT ON COLUMN MC_INGREDIENT.QUANTITY IS 'Weight or quantity';
COMMENT ON COLUMN MC_INGREDIENT.QUANTITY_TYPE IS 'Type of quantity (grams for weight, pieces for quantity)';
COMMENT ON COLUMN MC_INGREDIENT.COST IS 'Cost in rubles';

CREATE TABLE MC_RECIPE (
    ID              BIGINT          NOT NULL PRIMARY KEY ,
    USER_ID         BIGINT          NOT NULL ,
    NAME            VARCHAR(127)    NOT NULL ,
    SQUARE          NUMERIC(8,2)    NOT NULL ,
    CAKE            NUMERIC(8,0)    NOT NULL ,
    CREATION_DATE   TIMESTAMP       NOT NULL ,
    FAVORITE        BOOLEAN         DEFAULT FALSE NOT NULL ,
    PRIVACY         VARCHAR(16)     DEFAULT 'ALL' NOT NULL
);
CREATE SEQUENCE MC_RECIPE_SEQ INCREMENT BY 1 MINVALUE 1000000;

COMMENT ON COLUMN MC_RECIPE.ID IS 'Row id';
COMMENT ON COLUMN MC_RECIPE.USER_ID IS 'Recipe owner id';
COMMENT ON COLUMN MC_RECIPE.NAME IS 'Recipe name';
COMMENT ON COLUMN MC_RECIPE.SQUARE IS 'Cake square (e.g. square, circle) in square centimeters';
COMMENT ON COLUMN MC_RECIPE.CAKE IS 'Number of cakes (layers) into product';
COMMENT ON COLUMN MC_RECIPE.CREATION_DATE IS 'Recipe first creation date';
COMMENT ON COLUMN MC_RECIPE.FAVORITE IS 'Is recipe favourite for the owner';

CREATE TABLE MC_COMPONENT (
    ID              BIGINT          NOT NULL PRIMARY KEY ,
    RECIPE_ID       BIGINT          NOT NULL REFERENCES MC_RECIPE(ID),
    NAME            VARCHAR(127)    NOT NULL,
    TYPE            VARCHAR(16)     NOT NULL,
    QUANTITY        NUMERIC(8,2)    NOT NULL DEFAULT 1
);
CREATE SEQUENCE MC_COMPONENT_SEQ INCREMENT BY 1 MINVALUE 1000000;

COMMENT ON COLUMN MC_COMPONENT.ID IS 'Row id';
COMMENT ON COLUMN MC_COMPONENT.RECIPE_ID IS 'Components`s recipe id';
COMMENT ON COLUMN MC_COMPONENT.NAME IS 'Component name';
COMMENT ON COLUMN MC_COMPONENT.TYPE IS 'Component type (for example: cream, cake etc)';
COMMENT ON COLUMN MC_COMPONENT.QUANTITY IS 'Component`s quantity need for recipe';

CREATE TABLE MC_RECIPE_INGREDIENT (
    ID              BIGINT          NOT NULL PRIMARY KEY ,
    COMPONENT_ID    BIGINT          NOT NULL REFERENCES MC_COMPONENT(ID),
    INGREDIENT_ID   BIGINT          NOT NULL REFERENCES MC_INGREDIENT(ID),
    QUANTITY        NUMERIC(6,2)    NOT NULL
);
CREATE SEQUENCE MC_RECIPE_INGREDIENT_SEQ INCREMENT BY 1 MINVALUE 1000000;

COMMENT ON COLUMN MC_RECIPE_INGREDIENT.ID IS 'Row id';
COMMENT ON COLUMN MC_RECIPE_INGREDIENT.COMPONENT_ID IS 'Ingredient`s component id';
COMMENT ON COLUMN MC_RECIPE_INGREDIENT.INGREDIENT_ID IS 'Base ingredient`s id';
COMMENT ON COLUMN MC_RECIPE_INGREDIENT.QUANTITY IS 'Weight or quantity';