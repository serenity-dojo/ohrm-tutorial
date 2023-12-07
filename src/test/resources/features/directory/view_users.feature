Feature: View users in the directory

  Rule: Users can view users in the directory
    Example: Viewing the default user list
      Given Amy is logged in as an admin
      When Amy views the user directory
      Then she should see the following users:
        | Name                | Role                    |
        | Odis Adalwin        | Chief Technical Officer |
        | Peter Mac Anderson  | Chief Financial Officer |
        | Linda Jane Anderson | VP - Sales & Marketing  |

#    Example: Viewing all the users by scrolling down
#      Given I am logged in as an admin
#      When I view the user directory
#      And I scroll to the last user
#      Then additional users should be loaded
