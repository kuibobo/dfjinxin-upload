<!doctype html>
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Content-Language" content="en" />
    <meta name="msapplication-TileColor" content="#2d89ef">
    <meta name="theme-color" content="#4188c9">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="HandheldFriendly" content="True">
    <meta name="MobileOptimized" content="320">
    <link rel="icon" href="./favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" type="image/x-icon" href="${request.contextPath}/assets/app/img/favicon.svg" />
    <title>文件上传</title>
    <script src="${request.contextPath}/assets/tabler/js/require.min.js"></script>
    <script>
        requirejs.config({
            baseUrl: '/assets'
        });
    </script>
    <!-- Dashboard Core -->
    <link href="${request.contextPath}/assets/tabler/css/dashboard.css?v=2020-0601b" rel="stylesheet" />
    <script src="${request.contextPath}/assets/tabler/js/dashboard.js"></script>
</head>
<body class="">
<div class="page">
    <div class="page-main">
        <div class="header">
            <div class="container2" style="padding:0 0 0 24px">
                <div class="d-flex">
                    <embed src="${request.contextPath}/assets/app/img/logo.svg"
                           style="margin:10px 10px 0 0"
                           width="28" height="28" type="image/svg+xml" />
                    <a class="header-brand" href="${request.contextPath}/attachment/list">
                        文件上传服务
                    </a>

                    <div class="container">
                        <div class="row align-items-center">
                            <div class="col-lg-3 ml-auto">
                            </div>
                            <div class="col-lg order-lg-first">
                                <ul class="nav nav-tabs border-0 flex-column flex-lg-row">
                                    <li class="nav-item ${(uri == "/attachment/list")?string("active", "")}">
                                        <a href="${request.contextPath}/attachment/list" class="nav-link "> 首页</a>
                                    </li>
                                    <#if currentUser.getAdmin()>
                                        <li class="nav-item ${(uri == "/user/list")?string("active", "")}">
                                            <a href="${request.contextPath}/user/list" class="nav-link"> 用户列表</a>
                                        </li>
                                    </#if>
                                </ul>
                            </div>
                        </div>
                    </div>


                    <div class="d-flex order-lg-2 ml-auto" style="margin:10px 0 0 0">
                        <div class="dropdown">
                            <a href="#" class="nav-link pr-0 leading-none" data-toggle="dropdown">
                                <span class="avatar" style="background-size: 80% 80%;background-image: url(${request.contextPath}/assets/app/img/avatar.svg); background-color:#fff;"></span>
                                <span class="ml-2 d-none d-lg-block">
                                  <span class="text-default">${currentUser.name}</span>
                                  <small class="text-muted d-block mt-1"></small>
                                </span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow" style="background-color: #1a3275">
                                <a class="dropdown-item" href="${request.contextPath}/logout">
                                      退出
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="my-3 my-md-5">
