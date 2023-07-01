package com.snapp.pay.auth.mapper;

import com.snapp.pay.auth.model.User;
import com.snapp.pay.auth.payload.request.UserRegistrationRequest;
import com.snapp.pay.auth.payload.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toUser(UserRegistrationRequest userRegistrationRequest);

    @Mapping(target = "role", source = "user.role.name")
    UserResponse toUserResponse(User user);

}
