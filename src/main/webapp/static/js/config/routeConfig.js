/**
 * @author JASON GAO[jason.gao@adara.com ]
 * @author YI ZHAO[yi.zhao@adara.com]
 */

app.config(['$routeProvider', '$locationProvider',
    function ($routeProvider, $locationProvider) {
        var base = '/';

        // use the HTML5 History API
        $locationProvider.html5Mode(true);

        $routeProvider
            .when('/', {
                title: 'Adobe Mappings',
                templateUrl: 'static/html/adobe-pages/mappings.html',
                controller: 'listCtrlAdobe'
            })
            .when(base + 'login', {
                templateUrl: 'static/html/usermanagement/login/login.view.html',
                controller: 'LoginController',
                controllerAs: 'vm'
            })
            .when(base + 'register', {
                templateUrl: 'static/html/usermanagement/register/register.view.html',
                controller: 'RegisterController',
                controllerAs: 'vm'
            })
            .when(base + 'manageusers', {
                templateUrl: 'static/html/usermanagement/manage-users/manageusers.view.html',
                controller: 'ManageusersController',
                controllerAs: 'vm'
            })
            .when(base + 'adobe', {
                title: 'Adobe Mappings',
                templateUrl: 'static/html/adobe-pages/mappings.html',
                controller: 'listCtrlAdobe'
            })
            .when(base + 'adobe/edit-mapping/:mappingID', {
                title: 'Edit Adobe Mappings',
                templateUrl: 'static/html/adobe-pages/edit-mapping.html',
                controller: 'editCtrlAdobe',
                resolve: {
                    backendData: function (adobeService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return adobeService.getMapping(mappingID);
                    }
                }
            })
            .when(base + 'derive-conversion', {
                title: 'Derive Combo Pixel Mappings',
                templateUrl: 'static/html/derive-conversion-pages/mappings.html',
                controller: 'listCtrlDeriveConversion'
            })
            .when(base + 'derive-conversion/edit-mapping/:mappingID', {
                title: 'Edit Derive Combo Pixel Mappings',
                templateUrl: 'static/html/derive-conversion-pages/edit-mapping.html',
                controller: 'editCtrlDeriveConversion',
                resolve: {
                    backendData: function (deriveConversionService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return deriveConversionService.getMapping(mappingID);
                    }
                }
            })
            .when(base + 'krux-dpkey', {
                title: 'Krux Dpkey Mappings',
                templateUrl: 'static/html/krux-dpkey-pages/mappings.html',
                controller: 'listCtrlKruxDpkey'
            })
            .when(base + 'krux-dpkey/edit-mapping/:mappingID', {
                title: 'Edit Krux Dpkey Mappings',
                templateUrl: 'static/html/krux-dpkey-pages/edit-mapping.html',
                controller: 'editCtrlKruxDpkey',
                resolve: {
                    backendData: function (kruxService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return kruxService.getMapping(mappingID);
                    }
                }
            })

            .when(base + 'liveramp', {
                title: 'Liveramp Mappings',
                templateUrl: 'static/html/liveramp-pages/mappings.html',
                controller: 'listCtrlLiveramp'
            })
            .when(base + 'liveramp/edit-dp-mapping/:mappingID', {
                title: 'Edit Liveramp DP Mappings',
                templateUrl: 'static/html/liveramp-pages/edit-dp-mapping.html',
                controller: 'editCtrlLiverampDp',
                resolve: {
                    backendData: function (liverampDpService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return liverampDpService.getMapping(mappingID);
                    }
                }
            })
            .when(base + 'liveramp/edit-key-mapping/:mappingID', {
                title: 'Edit Liveramp Key Mappings',
                templateUrl: 'static/html/liveramp-pages/edit-key-mapping.html',
                controller: 'editCtrlLiverampKey',
                resolve: {
                    backendData: function (liverampDpKeyService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return liverampDpKeyService.getMapping(mappingID);
                    }
                }
            })

            .when(base + 'dbm', {
                title: 'Dbm Mappings',
                templateUrl: 'static/html/dbm-pages/mappings.html',
                controller: 'listCtrlDbm'
            })
            .when(base + 'dbm/edit-mapping/:mappingID', {
                title: 'Edit Dbm Mappings',
                templateUrl: 'static/html/dbm-pages/edit-mapping.html',
                controller: 'editCtrlDbm',
                resolve: {
                    backendData: function (dbmService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return dbmService.getMapping(mappingID);
                    }
                }
            })

            .when(base + 'refer-url-key-mappings', {
                title: 'refer_url_key_mappings',
                templateUrl: 'static/html/refer-url-key-mappings-pages/mappings.html',
                controller: 'listCtrlReferUrlKeyMappings'
            })
            .when(base + 'refer-url-key-mappings/edit-mapping/:mappingID', {
                title: 'Edit refer_url_key_mappings',
                templateUrl: 'static/html/refer-url-key-mappings-pages/edit-mapping.html',
                controller: 'editCtrlReferUrlKeyMappings',
                resolve: {
                    backendData: function (referUrlKeyMappingsService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return referUrlKeyMappingsService.getMapping(mappingID);
                    }
                }
            })

            .when(base + 'facebook', {
                title: 'Facebook Mappings',
                templateUrl: 'static/html/facebook-pages/mappings.html',
                controller: 'listCtrlFacebook'
            })
            .when(base + 'facebook/edit-pixel-mapping/:mappingID', {
                title: 'Edit Facebook Pixel Mappings',
                templateUrl: 'static/html/facebook-pages/edit-pixel-mapping.html',
                controller: 'editCtrlFacebookPixel',
                resolve: {
                    backendData: function (facebookDataProviderFacebookPixelsService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return facebookDataProviderFacebookPixelsService.getMapping(mappingID);
                    }
                }
            })
            .when(base + 'facebook/edit-dp-mapping/:mappingID', {
                title: 'Edit Facebook DP Mappings',
                templateUrl: 'static/html/facebook-pages/edit-dp-mapping.html',
                controller: 'editCtrlFacebookDp',
                resolve: {
                    backendData: function (facebookDataProvidersService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return facebookDataProvidersService.getMapping(mappingID);
                    }
                }
            })
            .when(base + 'facebook/edit-key-mapping/:mappingID', {
                title: 'Edit Facebook Key Mappings',
                templateUrl: 'static/html/facebook-pages/edit-key-mapping.html',
                controller: 'editCtrlFacebookKey',
                resolve: {
                    backendData: function (facebookFacebookDpkeysService, $route) {
                        var mappingID = $route.current.params.mappingID;
                        return facebookFacebookDpkeysService.getMapping(mappingID);
                    }
                }
            })
            .when(base + 'pixel-data-engine-group', {
                title: 'Pixel Data Engine Groups',
                templateUrl: 'static/html/pixel-data-engine-config/list-group.html',
                controller: 'listPixelGroups'
            })
            .when(base + 'group/edit-group/triggerkeyid=:keyId', {
                title: 'Edit Pixel Data Engine Group',
                templateUrl: 'static/html/pixel-data-engine-config/edit-group.html',
                controller: 'editPixelGroup',
                resolve: {
                    backendData: function (pixelmappingService, $route) {
                        var keyId = $route.current.params.keyId;
                        return pixelmappingService.getGroup(keyId);
                    }
                }
            })
            .when(base + 'group/edit-rules/triggerkeyid=:trigger_key_id&gid=:gid', {
                title: 'Group Id :gid',
                templateUrl: 'static/html/pixel-data-engine-config/edit-rules.html',
                controller: 'editSameGroup',
                resolve: {
                    backendData: function (pixelmappingService, $route) {
                        var gid = $route.current.params.gid;
                        var triggerKeyId = $route.current.params.trigger_key_id;
                        return pixelmappingService.getSameGroup(triggerKeyId, gid);
                    }
                }
            })
            .when(base + 'pixel-data-engine-rule', {
                title: 'Pixel Data Engine Rules',
                templateUrl: 'static/html/pixel-data-engine-config/list-rule.html',
                controller: 'listPixelRules'
            })
            /*.when(base + 'rule/edit-rule/triggerkeyid=:keyId', {
             title: 'Edit Pixel Data Engine Rule',
             templateUrl: 'static/html/pixel-data-engine-config/edit-rule.html',
             controller: 'editPixelRule',
             resolve: {
             backendData: function (pixelmappingService, $route) {
             var gid = $route.current.params.gid;
             var keyId = $route.current.params.keyId;
             var priority = $route.current.params.priority;
             return pixelmappingService.getRule(gid, keyId, priority);
             }
             }
             })*/
            .when(base + 'geo-file-manager', {
                title: 'Geo File Manager',
                templateUrl: 'static/html/geo-file-manager/list-pixel-data-engine-maps.html',
                controller: 'listGeoMapsController'
            })
            .when(base + 'geo/get-pde-map/tableName=:tableName', {
                templateUrl: 'static/html/geo-file-manager/get-pde-map.html',
                controller: 'getPdeMapController',
                //resolve: {
                //    backendData: function (geoFileManagerService, $route) {
                //        var map_name = $route.current.params.map_name;
                //        return geoFileManagerService.createCSVFromTable(map_name);
                //    }
                //}

            })
            .when(base + 'geo/edit-geo/mapname=:mapname&action=:action', {

                templateUrl: 'static/html/geo-file-manager/edit-geo.html',
                controller: 'editGeoMapController',
                resolve: {
                    backendData: function (geoFileManagerService, $route) {
                        var mapname = $route.current.params.mapname;
                        return geoFileManagerService.getPixelDataEngineMap(mapname);
                    }
                }
            })
            .otherwise({
                templateUrl: 'static/html/usermanagement/login/login.view.html',
                redirectTo: base + 'login'
            });
    }]);
