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
    <link rel="shortcut icon" type="image/x-icon" href="./favicon.ico" />
    <title>上传</title>
    <script src="${request.contextPath}/assets/tabler/js/require.min.js"></script>
    <script>
        requirejs.config({
            baseUrl: '/assets'
        });
    </script>
    <!-- Dashboard Core -->
    <link href="${request.contextPath}/assets/tabler/css/dashboard.css" rel="stylesheet" />
    <script src="${request.contextPath}/assets/tabler/js/dashboard.js"></script>
</head>
<body class="">
<div class="page">
    <div class="page-main">
        <div class="header py-4">
            <div class="container">
                <div class="d-flex">
                    <a class="header-brand" href="./index.html">
                        <img src="${request.contextPath}/assets/tabler/images/tabler.svg" class="header-brand-img" alt="tabler logo">
                    </a>
                    <div class="d-flex order-lg-2 ml-auto">
                        <div class="dropdown">
                            <a href="#" class="nav-link pr-0 leading-none" data-toggle="dropdown">
                                <span class="avatar" style="background-image: url(${request.contextPath}/assets/tabler/images/avatar.png)"></span>
                                <span class="ml-2 d-none d-lg-block">
                                  <span class="text-default">${user.name}</span>
                                  <small class="text-muted d-block mt-1"></small>
                                </span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="header collapse d-lg-flex p-0" id="headerMenuCollapse">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-3 ml-auto">

                    </div>
                    <div class="col-lg order-lg-first">
                        <ul class="nav nav-tabs border-0 flex-column flex-lg-row">
                            <li class="nav-item">
                                <a href="${request.contextPath}/attachment/list" class="nav-link active"><i class="fe fe-home"></i> 首页</a>
                            </li>
                            <#if user.isAdmin()>
                            <li class="nav-item">
                                <a href="${request.contextPath}/user/list" class="nav-link"><i class="fe fe-users"></i> 用户列表</a>
                            </li>
                            </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="my-3 my-md-5">
