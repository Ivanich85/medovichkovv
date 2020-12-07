package medovichkovvcalculationservice.enums;

import lombok.Getter;

/**
 * Defines, who can watch user`s recipes
 *
 * ALL - all users
 * FRIEND - owner`s friends only
 * NONE - nobody but the owner only
 **/
@Getter
public enum PrivacyType {
    ALL("Все"),
    FRIEND("Только друзья"),
    NONE("Никто");

    private String desc;

    PrivacyType(String desc) {
        this.desc = desc;
    }
}
