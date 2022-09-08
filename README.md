# RK-RC-DW-2022-HACKATHON
Ravali Kuppachi and Rajabhushan Ceelam Project for DeveloperWeek Cloud 2022 Hackathon

![alt text](https://github.com/bhushanraja/rk-rc-dw-2022-hackathon/blob/main/blueline-ui/src/images/blue-line-taxis.jpeg?raw=true)
## BlueLine is an online taxi booking service.

### blueline-ui
This is the frontend application developed using Angular

### blueline-api
This is the backend application developed using SpringBoot and MySQL

## OCI Env Prerequisites

- Permission to `manage` the following types of resources in your Oracle Cloud Infrastructure tenancy: `vcns`, `internet-gateways`, `load-balancers`, `route-tables`, `security-lists`, `subnets`, and `instances`.

- Quota to create the following resources: 1 VCN, 3 subnets, 1 Internet Gateway, 1 NAT Gateway, 2 route rules, ...

If you don't have the required permissions and quota, contact your tenancy administrator. See [Policy Reference](https://docs.cloud.oracle.com/en-us/iaas/Content/Identity/Reference/policyreference.htm), [Service Limits](https://docs.cloud.oracle.com/en-us/iaas/Content/General/Concepts/servicelimits.htm), [Compartment Quotas](https://docs.cloud.oracle.com/iaas/Content/General/Concepts/resourcequotas.htm).

## Deploy Using Oracle Resource Manager

1. Click [![Deploy to Oracle Cloud](https://oci-resourcemanager-plugin.plugins.oci.oraclecloud.com/latest/deploy-to-oracle-cloud.svg)](https://cloud.oracle.com/resourcemanager/stacks/create?region=home&zipUrl=https://github.com/bhushanraja/rk-rc-dw-2022-hackathon/tree/main/blueline-api/releases/latest/download/oci-arch-devops-oke-stack-latest.zip)

   If you aren't already signed in, when prompted, enter the tenancy and user credentials.

2. Review and accept the terms and conditions.

3. Select the region where you want to deploy the stack.

4. Follow the on-screen prompts and instructions to create the stack.

5. After creating the stack, click **Terraform Actions**, and select **Plan**.

6. Wait for the job to be completed, and review the plan.

   To make any changes, return to the Stack Details page, click **Edit Stack**, and make the required changes. Then, run the **Plan** action again.

7. If no further changes are necessary, return to the Stack Details page, click **Terraform Actions**, and select **Apply**.

## Deploy Using the Terraform CLI

### Clone the Repo

Now, you'll want a local copy of this repo. You can make that with the commands:

```
    git clone https://github.com/bhushanraja/rk-rc-dw-2022-hackathon.git
    cd blueline-devops
    ls
```

### Prerequisites
First off, you'll need to do some pre-deploy setup.  That's all detailed [here](https://github.com/cloud-partners/oci-prerequisites).

Secondly, a sample `terraform.tfvars` file is provided in the repo. Please update the file with your tenancy details.

Alternatively, you can create a `terraform.tfvars` file and populate with the following information as well as any additional information for your deployment:

```
# Authentication
tenancy_ocid         = "<tenancy_ocid>"
user_ocid            = "<user_ocid>"
fingerprint          = "<finger_print>"
private_key_path     = "<pem_private_key_path>"

# Region
region = "<oci_region>"

# Availablity Domain 
availablity_domain_name = "<availablity_domain_name>"

````

### Create the Resources
Run the following commands:

    terraform init
    terraform plan
    terraform apply


### Destroy the Deployment
When you no longer need the deployment, you can run this command to destroy the resources:

    terraform destroy