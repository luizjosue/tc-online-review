CP=""
CP=$CP:/home/coder/online_review/lib/tcs/auto_pilot/1.0/auto_pilot.jar
CP=$CP:/home/coder/online_review/lib/tcs/base_exception/1.0/base_exception.jar
CP=$CP:/home/coder/online_review/lib/tcs/class_associations/1.0/class_associations.jar
CP=$CP:/home/coder/online_review/lib/tcs/configuration_manager/2.1.5/configuration_manager.jar
CP=$CP:/home/coder/online_review/lib/tcs/command_line_utility/1.0/command_line_utility.jar
CP=$CP:/home/coder/online_review/lib/tcs/database_abstraction/1.1/database_abstraction.jar
CP=$CP:/home/coder/online_review/lib/tcs/data_validation/1.0/data_validation.jar
CP=$CP:/home/coder/online_review/lib/tcs/db_connection_factory/1.0/db_connection_factory.jar
CP=$CP:/home/coder/online_review/lib/tcs/executable_wrapper/1.0/executable_wrapper.jar
CP=$CP:/home/coder/online_review/lib/tcs/id_generator/3.0/id_generator.jar
CP=$CP:/home/coder/online_review/lib/tcs/job_scheduler/1.0/job_scheduler.jar
CP=$CP:/home/coder/online_review/lib/tcs/logging_wrapper/1.2/logging_wrapper.jar
CP=$CP:/home/coder/online_review/lib/tcs/object_factory/2.0.1/object_factory.jar
CP=$CP:/home/coder/online_review/lib/tcs/project_management/1.0/project_management.jar
CP=$CP:/home/coder/online_review/lib/tcs/project_management_persistence/1.0/project_management_persistence.jar
CP=$CP:/home/coder/online_review/lib/tcs/phase_management/1.0.1/phase_management.jar
CP=$CP:/home/coder/online_review/lib/tcs/phase_management_persistence/1.0/phase_management_persistence.jar
CP=$CP:/home/coder/online_review/lib/tcs/project_phases/2.0/project_phases.jar
CP=$CP:/home/coder/online_review/lib/tcs/search_builder/1.3/search_builder.jar
CP=$CP:/home/coder/online_review/lib/tcs/typesafe_enum/1.0/typesafe_enum.jar
CP=$CP:/home/coder/online_review/lib/tcs/workdays/1.0/workdays.jar
CP=$CP:/home/coder/online_review/lib/third_party/xerces.jar
CP=$CP:/home/coder/online_review/lib/third_party/dom.jar
CP=$CP:/home/coder/online_review/lib/third_party/xml-api.jar
CP=$CP:/home/coder/online_review/lib/third_party/ifx-jdbc.jar

java -cp $CP com.topcoder.management.phase.autopilot.AutoPilotJob -config auto_pilot.xml -namespace AutoPilotJob -autopilot com.topcoder.management.phase.autopilot.AutoPilot -poll 5
