/**
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*   http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/

#ifndef INLONG_SDK_UTILS_H
#define INLONG_SDK_UTILS_H

#include <snappy.h>
#include <stdint.h>
#include <sys/select.h>
#include <sys/time.h>
#include <sys/types.h>
#include <unistd.h>
#include <string>
#include <utility>
#include <vector>
#include "atomic.h"
namespace inlong {
extern AtomicUInt g_send_msgid;
extern AtomicInt user_exit_flag;
class Utils {
 private:
  static char snowflake_id[35];
  static uint16_t sequence;
  static uint64_t last_msstamp;

 public:
  static void taskWaitTime(int32_t sec);
  static uint64_t getCurrentMsTime();
  static uint64_t getCurrentWsTime();
  static std::string getFormatTime(uint64_t date_time);
  static size_t zipData(const char* input, uint32_t input_len, std::string& zip_res);
  static char* getSnowflakeId();
  static bool getFirstIpAddr(std::string& local_host);
  inline static bool isLegalTime(uint64_t report_time)
  {
    return ((report_time > 1435101567000LL) && (report_time < 4103101567000LL));
  }
  static bool bindCPU(int32_t cpu_id);
  static int32_t requestUrl(const std::string& url, std::string& urlByDNS, std::string& res,
                            uint32_t timeout);
  static bool readFile(const std::string& file_path,
                       std::string& content);

  static int32_t splitOperate(const std::string& source, std::vector<std::string>& result,
                              const std::string& delimiter);
  static std::string getVectorStr(std::vector<std::string>& vs);

 private:
  static size_t getUrlResponse(void* buffer, size_t size, size_t count, void* response);
  static int64_t waitNextMills(int64_t last_ms);
  static std::string trim(const std::string& source);
  static bool parseHost(const std::string& host, std::string& ip);
  static bool getUrlByDNS(const std::string& url, std::string& ipUrl);
};

}  // namespace inlong

#endif  // INLONG_SDK_UTILS_H