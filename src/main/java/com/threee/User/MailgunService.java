package com.threee.User;

import com.sun.jersey.api.client.ClientResponse;

public interface MailgunService {
  ClientResponse sendSimpleMessage(com.threee.User.Users users);
  ClientResponse sendComplexMessage(com.threee.User.Users users);
}
