def deployToStaging(String appName, String version) {
    echo "Deploying ${appName}, version ${version} to the staging environment"
}

def deployToProduction(String appName, String version) {
    echo "Deploying ${appName}, version ${version} to the production environment"
}

return this;