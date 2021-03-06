/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#ifndef FLATBUFFERS_CODE_GENERATORS_H_
#define FLATBUFFERS_CODE_GENERATORS_H_

namespace flatbuffers {

class BaseGenerator {
 public:
  virtual bool generate() = 0;

  static const std::string NamespaceDir(const Parser &parser,
                                        const std::string &path,
                                        const Namespace &ns) {
    EnsureDirExists(path.c_str());
    if (parser.opts.one_file) return path;
    std::string namespace_dir = path;  // Either empty or ends in separator.
    auto namespaces = PrefixedNamespaceComponents(ns, parser.opts.lang);
    for (auto it = namespaces.begin(); it != namespaces.end(); ++it) {
      namespace_dir += *it + kPathSeparator;
      EnsureDirExists(namespace_dir.c_str());
    }
    return namespace_dir;
  }

 protected:
  BaseGenerator(const Parser &parser, const std::string &path,
                const std::string &file_name,
                const std::string qualifying_start,
                const std::string qualifying_separator)
      : parser_(parser),
        path_(path),
        file_name_(file_name),
        qualifying_start_(qualifying_start),
        qualifying_separator_(qualifying_separator){};
  virtual ~BaseGenerator(){};

  // No copy/assign.
  BaseGenerator &operator=(const BaseGenerator &);
  BaseGenerator(const BaseGenerator &);

  const std::string NamespaceDir(const Namespace &ns) {
    return BaseGenerator::NamespaceDir(parser_, path_, ns);
  }

  const char *FlatBuffersGeneratedWarning() {
    return "automatically generated by the FlatBuffers compiler,"
           " do not modify\n\n";
  }

  bool IsEverythingGenerated() {
    for (auto it = parser_.enums_.vec.begin(); it != parser_.enums_.vec.end();
         ++it) {
      if (!(*it)->generated) return false;
    }
    for (auto it = parser_.structs_.vec.begin();
         it != parser_.structs_.vec.end(); ++it) {
      if (!(*it)->generated) return false;
    }
    return true;
  }

  static void NamespacePrefixAsComponents(const std::string &prefix, std::vector<std::string> &namespaces)
  {
    std::string::size_type componentStart = 0;
    std::string::size_type componentEnd = prefix.find_first_of('.', componentStart);
    while (componentEnd != std::string::npos) {
      namespaces.push_back(prefix.substr(componentStart, componentEnd - componentStart));
      componentStart = componentEnd + 1;
      componentEnd = prefix.find_first_of('.', componentStart);
    }
    if (componentStart < prefix.length())
      namespaces.push_back(prefix.substr(componentStart, prefix.length() - componentStart));
  }

  // Returns namespace components, including the language specific prefix splitted by '.'
  static std::vector<std::string> PrefixedNamespaceComponents(const Namespace &ns, IDLOptions::Language language)
  {
    std::vector<std::string> namespaces;
    auto java_prefix = ns.attributes.Lookup("java_prefix");
    if (language == IDLOptions::kJava && java_prefix) {
      NamespacePrefixAsComponents(java_prefix->constant, namespaces);
    }
    namespaces.insert(namespaces.end(), ns.components.begin(), ns.components.end());
    return namespaces;
  }


  std::string FullNamespace(IDLOptions::Language language, const char *separator, const Namespace &ns) {
    std::string namespace_name;
    auto namespaces = PrefixedNamespaceComponents(ns, language);
    for (auto it = namespaces.begin(); it != namespaces.end(); ++it) {
      if (namespace_name.length()) namespace_name += separator;
      namespace_name += *it;
    }
    return namespace_name;
  }

  const std::string LastNamespacePart(const Namespace &ns) {
    auto &namespaces = ns.components;
    if (namespaces.size())
      return *(namespaces.end() - 1);
    else
      return std::string("");
  }

  // tracks the current namespace for early exit in WrapInNameSpace
  // c++, java and csharp returns a different namespace from
  // the following default (no early exit, always fully qualify),
  // which works for js and php
  virtual const Namespace *CurrentNameSpace() { return nullptr; }

  // Ensure that a type is prefixed with its namespace whenever it is used
  // outside of its namespace.
  std::string WrapInNameSpace(const Namespace *ns, const std::string &name) {
    if (CurrentNameSpace() == ns) return name;
    std::string qualified_name = qualifying_start_;
    for (auto it = ns->components.begin(); it != ns->components.end(); ++it)
      qualified_name += *it + qualifying_separator_;
    return qualified_name + name;
  }

  std::string WrapInNameSpace(const Definition &def) {
    return WrapInNameSpace(def.defined_namespace, def.name);
  }

  const Parser &parser_;
  const std::string &path_;
  const std::string &file_name_;
  const std::string qualifying_start_;
  const std::string qualifying_separator_;
};

}  // namespace flatbuffers

#endif  // FLATBUFFERS_CODE_GENERATORS_H_
