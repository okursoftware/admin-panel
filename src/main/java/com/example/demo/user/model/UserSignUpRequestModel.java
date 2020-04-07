package com.example.demo.user.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequestModel implements Serializable {


    @Pattern(regexp = "^(([A-za-z ğüşöçİĞÜŞÖÇ]+[\\s]{1}[A-za-z ğüşöçİĞÜŞÖÇ]+)|([A-Za-z]+))$", message = "Lütfen Uygun Bir İsim Giriniz")
    @Size(min = 5, max = 15,message = "Uzunlu sorunu isim" )
    private String name;


    @Pattern(regexp = "^[A-z ğüşöçİĞÜŞÖÇ](?!\\s)[a-z ğüşöçİĞÜŞÖÇ]*((\\W)?[A-Z İĞÜŞÖÇ][a-z ğüşöç]+)*$", message = "Uygun bir soyad giriniz")
    @Size(min = 5, max = 15,message = "Uzunlu sorunu soyad")
    private String surname;

    @Email(message = "Geçerli bir e-mail adresi giriniz")
    @NotBlank(message = "Geçerli bir e-mail adresi giriniz")
    private String email;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "Paralonız en az bir rakam  büyük harf ve özel karakter içermelidir")
    private String password;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "Paralo doğrulamanız en az bir rakam  büyük harf ve özel karakter içermelidir")
    private String confirmpassword;


}
