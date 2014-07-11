InstagramOAuthDemo
==================

Instagram’s API uses the OAuth 2.0 protocol for simple, but effective authentication and authorization. OAuth 2.0 is much easier to use than previous schemes; developers can start using the Instagram API almost immediately. 

The server will redirect the user in one of two ways that you choose:
Server-side flow (reccommended):Redirect the user to a URI of your choice. Take the provided code parameter and exchange it for an access_token by POSTing the code to our access_token url.
Implicit flow: Instead of handling a code, we include the access_token as a fragment (#) in the URL. This method allows applications without any server component to receive an access_token with ease.

This demo show two ways to get access token.



Developed By
============

* Jin Zhenguo - <jinzhenguo1990@gmail.com>



License
=======

    Copyright 2012 Jin Zhenguo

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



