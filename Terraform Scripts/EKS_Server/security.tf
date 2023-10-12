# Create Security Group - SSH Traffic and other ports
resource "aws_security_group" "web-traffic" {
  name = "My_Security_Group3"

  ingress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }


  tags = {
    "Name" = "My_SG333"
  }
}
