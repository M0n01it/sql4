package ru.netology.sql4.controller;

import org.springframework.security.access.annotation.Secured;
import jakarta.annotation.security.RolesAllowed ;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MethodSecurityController {

    // Доступ для пользователей с ролью READ.
    @Secured("ROLE_READ")
    @GetMapping("/read")
    public String readMethod() {
        return "Доступ разрешён для пользователей с ролью READ";
    }

    // Доступ для пользователей с ролью WRITE (используя @RolesAllowed).
    @RolesAllowed("WRITE")
    @GetMapping("/write")
    public String writeMethod() {
        return "Доступ разрешён для пользователей с ролью WRITE";
    }

    // Доступ для пользователей, у которых есть хотя бы одна из ролей WRITE или DELETE.
    @PreAuthorize("hasAnyRole('WRITE', 'DELETE')")
    @GetMapping("/write-delete")
    public String writeOrDeleteMethod() {
        return "Доступ разрешён для пользователей с ролью WRITE или DELETE";
    }

    // Доступ только если переданный параметр username совпадает с именем пользователя в Authentication.
    @PreAuthorize("#username == authentication.name")
    @GetMapping("/user")
    public String userSpecific(@RequestParam("username") String username) {
        return "Доступ разрешён для пользователя: " + username;
    }
}
