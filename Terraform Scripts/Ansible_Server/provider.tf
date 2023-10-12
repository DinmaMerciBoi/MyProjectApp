terraform {
  required_version = "~> 1.0"
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.0" # Optional but recommended in production
    }
  }

  backend "s3" {
    bucket = "project-register"
    key    = "ansible/terraform.tfstate"
    region = "us-east-2"

  }
}

provider "aws" {
  region = "us-east-2"
}

