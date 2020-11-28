package com.syh.example.rbacoopdemo.rbac.domain.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Resource {

    @Enumerated
    private ResourceType resourceType;

    private ResourceKey resourceKey;

    public static Resource menu(Menu menu) {
        return new Resource(ResourceType.MENU, new ResourceKey(menu.name()));
    }

    public boolean include(Resource that) {
        if (that.resourceType == ResourceType.MENU) {
            return Menu.valueOf(this.resourceKey.getKey())
                    .isParent(Menu.valueOf(that.getResourceKey().getKey()));
        } else {
            return this.resourceType == that.resourceType
                    && this.resourceKey.equals(that.resourceKey);
        }
    }
}
