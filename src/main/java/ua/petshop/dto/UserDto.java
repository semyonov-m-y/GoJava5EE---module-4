package ua.petshop.dto;

import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull
    String name;
    @NotNull
    String password;
    @NotNull
    String confirmPassword;

    public UserDto() {
    }

    public UserDto(String name, String password, String confirmPassword) {
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserDto userDto = (UserDto) o;

        if (name != null ? !name.equals(userDto.name) : userDto.name != null) {
            return false;
        }
        if (password != null ? !password.equals(userDto.password) : userDto.password != null) {
            return false;
        }
        return confirmPassword != null ? confirmPassword.equals(userDto.confirmPassword) : userDto.confirmPassword == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (confirmPassword != null ? confirmPassword.hashCode() : 0);
        return result;
    }
}
