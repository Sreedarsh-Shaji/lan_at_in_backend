package com.jobseeker.company.jobseekercompany.dao;

import com.jobseeker.company.jobseekercompany.utils.enums.ROLES;
import com.jobseeker.company.jobseekercompany.utils.password.PasswordHasher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin{
   private String uid;
   private String name;
   private String password;
   private String email;
   private String phone;
   private ROLES role = ROLES.ADMIN;

   public void encryptPassword()
   {
      this.password = PasswordHasher.hashPassword(this.password);
   }

}