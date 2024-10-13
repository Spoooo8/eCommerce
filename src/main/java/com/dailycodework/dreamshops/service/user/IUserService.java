package com.dailycodework.dreamshops.service.user;

import com.dailycodework.dreamshops.dto.UserDto;
import com.dailycodework.dreamshops.model.Users;
import com.dailycodework.dreamshops.model.Users;
import com.dailycodework.dreamshops.request.CreateUserRequest;
import com.dailycodework.dreamshops.request.UserUpdateRequest;

public interface IUserService {
    Users getUserById(Long userId);
    Users createUser(CreateUserRequest request);
    Users updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(Users user);

    Users getAuthenticatedUser();

}
