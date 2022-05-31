pipeline {
    agent {
        node {
            label 'maven'
        }

    }
    stages {
        stage('拉取代码') {
            agent none
            steps {
                container('maven') {
                    git(url: 'http://gitlab.pacteraedge.com/pacteraedge-develop/sky-demo.git', credentialsId: 'gitee-id', branch: 'master', changelog: true, poll: false)
                    sh 'ls -al'
                }

            }
        }

        stage('项目编译') {
            agent none
            steps {
                container('maven') {
                    sh 'ls'
                    sh 'mvn clean package -Dmaven.test.skip=true'
//                     sh 'ls ruoyi-auth/target'
                }

            }
        }

        stage('default-2') {
            parallel {
                stage('构建ruoyi-auth镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls ruoyi-auth/target'
                            sh 'docker build -t ruoyi-auth:latest -f ruoyi-auth/Dockerfile  ./ruoyi-auth/'
                        }

                    }
                }

                stage('构建ruoyi-modules-system镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls ruoyi-modules/ruoyi-system/target'
                            sh 'docker build -t ruoyi-modules-system:latest -f ruoyi-modules/ruoyi-system/Dockerfile  ./ruoyi-modules/ruoyi-system'
                        }

                    }
                }

                stage('构建 ruoyi-modules-job镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls ruoyi-modules/ruoyi-job/target'
                            sh 'docker build -t ruoyi-modules-job:latest -f ruoyi-modules/ruoyi-job/Dockerfile  ./ruoyi-modules/ruoyi-job'
                        }

                    }
                }

                stage('构建ruoyi-modules-file镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls ruoyi-modules/ruoyi-file/target'
                            sh 'docker build -t ruoyi-modules-file:latest -f ruoyi-modules/ruoyi-file/Dockerfile  ./ruoyi-modules/ruoyi-file/'
                        }

                    }
                }

                stage('构建ruoyi-modules-gen镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls ruoyi-modules/ruoyi-gen/target'
                            sh 'docker build -t ruoyi-modules-gen:latest -f ruoyi-modules/ruoyi-gen/Dockerfile  ./ruoyi-modules/ruoyi-gen/'
                        }

                    }
                }

                stage('构建ruoyi-visual-monitor镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls ruoyi-visual/ruoyi-monitor/target'
                            sh 'docker build -t ruoyi-visual-monitor:latest -f ruoyi-visual/ruoyi-monitor/Dockerfile  ./ruoyi-visual/ruoyi-monitor/'
                        }

                    }
                }


                stage('构建ruoyi-gateway镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls ruoyi-gateway/target'
                            sh 'docker build -t ruoyi-gateway:latest -f ruoyi-gateway/Dockerfile  ./ruoyi-gateway/'
                        }

                    }
                }



            }
        }

        stage('default-3') {
            parallel {
                stage('推送ruoyi-auth镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'harbor-cl' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag ruoyi-auth:latest $REGISTRY/$DOCKERHUB_NAMESPACE/ruoyi-auth:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/ruoyi-auth:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }

                stage('推送ruoyi-modules-system镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'harbor-cl' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag ruoyi-modules-system:latest $REGISTRY/$DOCKERHUB_NAMESPACE/ruoyi-modules-system:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/ruoyi-modules-system:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }

                stage('推送ruoyi-modules-job镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'harbor-cl' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag ruoyi-modules-job:latest $REGISTRY/$DOCKERHUB_NAMESPACE/ruoyi-modules-job:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/ruoyi-modules-job:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }

                stage('推送ruoyi-modules-file镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'harbor-cl' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag ruoyi-modules-file:latest $REGISTRY/$DOCKERHUB_NAMESPACE/ruoyi-modules-file:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/ruoyi-modules-file:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }

                stage('推送ruoyi-modules-gen镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'harbor-cl' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag ruoyi-modules-gen:latest $REGISTRY/$DOCKERHUB_NAMESPACE/ruoyi-modules-gen:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/ruoyi-modules-gen:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }

                stage('推送ruoyi-visual-monitor镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'harbor-cl' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag ruoyi-visual-monitor:latest $REGISTRY/$DOCKERHUB_NAMESPACE/ruoyi-visual-monitor:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/ruoyi-visual-monitor:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }

                stage('推送ruoyi-gateway镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'harbor-cl' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag ruoyi-gateway:latest $REGISTRY/$DOCKERHUB_NAMESPACE/ruoyi-gateway:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/ruoyi-gateway:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }


            }
        }

        stage('default-4') {
            parallel {

                stage('ruoyi-auth - 部署到dev环境') {
                      agent none
                      steps {
                         container ('maven') {
                            withCredentials([
                              kubeconfigFile(
                                credentialsId: env.KUBECONFIG_CREDENTIAL_ID,
                                variable: 'KUBECONFIG')
                              ]) {
                              sh 'envsubst < ruoyi-auth/deploy/deploy.yml | kubectl apply -f -'
                            }
                         }
                      }
                }

                stage('ruoyi-gateway - 部署到dev环境') {
                      agent none
                      steps {
                         container ('maven') {
                            withCredentials([
                              kubeconfigFile(
                                credentialsId: env.KUBECONFIG_CREDENTIAL_ID,
                                variable: 'KUBECONFIG')
                              ]) {
                              sh 'envsubst < ruoyi-gateway/deploy/deploy.yml | kubectl apply -f -'
                            }
                         }
                      }
                }

                stage('ruoyi-job - 部署到dev环境') {
                      agent none
                      steps {
                         container ('maven') {
                            withCredentials([
                              kubeconfigFile(
                                credentialsId: env.KUBECONFIG_CREDENTIAL_ID,
                                variable: 'KUBECONFIG')
                              ]) {
                              sh 'envsubst < ruoyi-modules/ruoyi-job/deploy/deploy.yml | kubectl apply -f -'
                            }
                         }
                      }
                }

                stage('ruoyi-modules-file - 部署到dev环境') {
                      agent none
                      steps {
                         container ('maven') {
                            withCredentials([
                              kubeconfigFile(
                                credentialsId: env.KUBECONFIG_CREDENTIAL_ID,
                                variable: 'KUBECONFIG')
                              ]) {
                              sh 'envsubst < ruoyi-modules/ruoyi-file/deploy/deploy.yml | kubectl apply -f -'
                            }
                         }
                      }
                }

                stage('ruoyi-modules-gen - 部署到dev环境') {
                      agent none
                      steps {
                         container ('maven') {
                            withCredentials([
                              kubeconfigFile(
                                credentialsId: env.KUBECONFIG_CREDENTIAL_ID,
                                variable: 'KUBECONFIG')
                              ]) {
                              sh 'envsubst < ruoyi-modules/ruoyi-gen/deploy/deploy.yml | kubectl apply -f -'
                            }
                         }
                      }
                }

                stage('ruoyi-visual-monitor - 部署到dev环境') {
                      agent none
                      steps {
                         container ('maven') {
                            withCredentials([
                              kubeconfigFile(
                                credentialsId: env.KUBECONFIG_CREDENTIAL_ID,
                                variable: 'KUBECONFIG')
                              ]) {
                              sh 'envsubst < ruoyi-visual/ruoyi-monitor/deploy/deploy.yml | kubectl apply -f -'
                            }
                         }
                      }
                }


                stage('ruoyi-modules-system - 部署到dev环境') {\
                      agent none
                      steps {
                         container ('maven') {
                            withCredentials([
                              kubeconfigFile(
                                credentialsId: env.KUBECONFIG_CREDENTIAL_ID,
                                variable: 'KUBECONFIG')
                              ]) {
                              sh 'envsubst < ruoyi-modules/ruoyi-system/deploy/deploy.yml | kubectl apply -f -'
                            }
                         }
                      }
                }



            }
        }

        //1、配置全系统的邮件：                   全系统的监控
        //2、修改ks-jenkins的配置，里面的邮件；   流水线发邮件
        stage('发送确认邮件') {
            agent none
            steps {
                mail(to: 'xxxx', subject: '构建结果', body: "构建成功了  $BUILD_NUMBER")
            }
        }

    }
    environment {
        DOCKER_CREDENTIAL_ID = 'dockerhub-id'
        GITHUB_CREDENTIAL_ID = 'github-id'
        KUBECONFIG_CREDENTIAL_ID = 'demo-kubeconfig'
        REGISTRY = 'harbor.bk.rfios.com'
        DOCKERHUB_NAMESPACE = 'ks-devops-harbor'
        GITHUB_ACCOUNT = 'kubesphere'
        APP_NAME = 'devops-java-sample'
        ALIYUNHUB_NAMESPACE = 'ks-devops-harbor'
        HARBOR_CREDENTIAL = credentials('harbor-cl')
    }
    parameters {
        string(name: 'TAG_NAME', defaultValue: '', description: '')
    }
}
